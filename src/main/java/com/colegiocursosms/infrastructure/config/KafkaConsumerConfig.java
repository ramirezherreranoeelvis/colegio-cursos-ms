package com.colegiocursosms.infrastructure.config;

import com.colegiocursosms.infrastructure.input.kafka.dto.EnrollmentCreatedEvent;
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

@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfig {

      private final KafkaProperties kafkaProperties;

      @Bean
      public ConsumerFactory<String, EnrollmentCreatedEvent> enrollmentConsumerFactory() {
            var configs = new HashMap<String, Object>();
            configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
            configs.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());

            var deserializer = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(EnrollmentCreatedEvent.class, false));

            return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);
      }

      @Bean
      public ConcurrentKafkaListenerContainerFactory<String, EnrollmentCreatedEvent> enrollmentContainerFactory(
            ConsumerFactory<String, EnrollmentCreatedEvent> enrollmentConsumerFactory) {
            var factory = new ConcurrentKafkaListenerContainerFactory<String, EnrollmentCreatedEvent>();
            factory.setConsumerFactory(enrollmentConsumerFactory);
            return factory;
      }
}