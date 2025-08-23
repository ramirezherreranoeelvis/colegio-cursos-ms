package com.colegiocursosms.infrastructure.config.kafka.consumer;

import com.colegiocursosms.infrastructure.input.notification.dto.TeacherCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class TeacherKafkaConsumerConfig {

      private final KafkaProperties kafkaProperties;

      @Bean
      public ConsumerFactory<String, TeacherCreatedEvent> teacherConsumerFactory() {
            var configs = new HashMap<String, Object>();
            configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
            configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

            var deserializer = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(TeacherCreatedEvent.class, false));
            return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);
      }

      @Bean
      public ConcurrentKafkaListenerContainerFactory<String, TeacherCreatedEvent> teacherContainerFactory(
            ConsumerFactory<String, TeacherCreatedEvent> teacherConsumerFactory) {
            var factory = new ConcurrentKafkaListenerContainerFactory<String, TeacherCreatedEvent>();
            factory.setConsumerFactory(teacherConsumerFactory);
            // Aquí podríamos añadir un interceptor de validación si fuera necesario en el futuro
            return factory;
      }
}