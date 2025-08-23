package com.colegiocursosms.infrastructure.config;

import com.colegiocursosms.infrastructure.input.notification.dto.EnrollmentCreatedEvent;
import com.colegiocursosms.infrastructure.input.notification.dto.StudentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.AdminClientConfig;
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
@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfig {

      private final KafkaProperties kafkaProperties;

      @Bean
      public ConsumerFactory<String, EnrollmentCreatedEvent> consumerFactory() {
            var configs = new HashMap<String, Object>();
            configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
            configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

            var deserializer = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(EnrollmentCreatedEvent.class, false));

            return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);
      }

      @Bean
      public ConcurrentKafkaListenerContainerFactory<String, EnrollmentCreatedEvent> enrollmentContainerFactory(ConsumerFactory<String, EnrollmentCreatedEvent> enrollmentConsumerFactory) {
            var factory = new ConcurrentKafkaListenerContainerFactory<String, EnrollmentCreatedEvent>();
            factory.setConsumerFactory(enrollmentConsumerFactory);
            factory.setRecordInterceptor(validateEnrollmentEvent());
            return factory;
      }

      private RecordInterceptor<String, EnrollmentCreatedEvent> validateEnrollmentEvent() {
            // Define el predicado de validación
            Predicate<EnrollmentCreatedEvent> isEnrollmentValid = event ->
                  event != null &&
                  event.getGrade() != null && // <-- Tu validación de 'grade'
                  event.getYear() != null;     // <-- Tu validación de 'year'

            return ((record, consumer) -> {
                  if (isEnrollmentValid.test(record.value())) {
                        log.info("Evento de Matrícula validado correctamente. Campos requeridos presentes.");
                        return record; // El mensaje es válido y pasa al listener
                  }

                  log.warn("Evento de Matrícula descartado. Faltan 'grade' o 'year': {}", record.value());
                  return null; // El mensaje es inválido y se descarta. No llegará al listener.
            });
      }

      @Bean
      public ConsumerFactory<String, StudentCreatedEvent> studentConsumerFactory() {
            var configs = new HashMap<String, Object>();
            configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
            configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

            var deserializer = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(StudentCreatedEvent.class, false));
            return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);
      }

      @Bean
      public ConcurrentKafkaListenerContainerFactory<String, StudentCreatedEvent> studentContainerFactory(
            ConsumerFactory<String, StudentCreatedEvent> studentConsumerFactory) {
            var factory = new ConcurrentKafkaListenerContainerFactory<String, StudentCreatedEvent>();
            factory.setConsumerFactory(studentConsumerFactory);
            return factory;
      }

}