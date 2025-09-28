package com.colegiocursosms.infrastructure.config.kafka.consumer;

import com.colegiocursosms.infrastructure.input.notification.dto.StudentEnrolledEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.function.Predicate;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class StudentEnrolledKafkaConsumerConfig {

      private final KafkaProperties kafkaProperties;

      @Bean
      public ConsumerFactory<String, StudentEnrolledEvent> studentEnrolledConsumerFactory() {
            var configs = new HashMap<String, Object>();
            configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
            configs.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());

            var deserializer = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(StudentEnrolledEvent.class, false));
            return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);
      }

      @Bean
      public ConcurrentKafkaListenerContainerFactory<String, StudentEnrolledEvent> studentEnrolledContainerFactory(
            ConsumerFactory<String, StudentEnrolledEvent> studentEnrolledConsumerFactory) {
            var factory = new ConcurrentKafkaListenerContainerFactory<String, StudentEnrolledEvent>();
            factory.setConsumerFactory(studentEnrolledConsumerFactory);
            return factory;
      }

      private RecordInterceptor<String, StudentEnrolledEvent> validateStudentEnrolledEvent() {
            // 1. Definimos la regla de validación
            Predicate<StudentEnrolledEvent> isEventValid = event ->
                  event != null &&
                  event.getStudentId() != null && !event.getStudentId().isBlank() &&
                  event.getEnrollmentId() != null && !event.getEnrollmentId().isBlank();

            // 2. Creamos el interceptor que usa esa regla
            return (record, consumer) -> {
                  if (isEventValid.test(record.value())) {
                        log.info("Evento StudentEnrolledEvent validado correctamente.");
                        return record; // El mensaje es válido y pasa al listener
                  }

                  log.warn("Evento StudentEnrolledEvent descartado. Faltan studentId o enrollmentId: {}", record.value());
                  return null; // El mensaje es inválido y se descarta
            };
      }
}