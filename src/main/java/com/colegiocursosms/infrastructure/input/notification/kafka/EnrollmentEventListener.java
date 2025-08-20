package com.colegiocursosms.infrastructure.input.notification;

import com.colegiocursosms.application.port.input.IProcessEnrollmentUseCase;
import com.colegiocursosms.infrastructure.input.notification.dto.EnrollmentCreatedEvent;
import com.colegiocursosms.infrastructure.input.notification.mapper.KafkaEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class EnrollmentEventListener {

      private final IProcessEnrollmentUseCase processEnrollmentUseCase;
      private final KafkaEventMapper kafkaEventMapper;

      /**
       * Escucha el topic de matrículas creadas.
       * Al recibir un evento, lo convierte a un objeto de dominio y lo procesa
       * para crear o actualizar la réplica local.
       */
      @KafkaListener(
            topics = "${spring.kafka.topics.enrollments-created}",
            containerFactory = "enrollmentContainerFactory"
      )
      public void listenEnrollmentCreated(EnrollmentCreatedEvent event) {
            log.info("Evento EnrollmentCreatedEvent recibido para la matrícula ID: {}", event.getId());

            var enrollmentDomain = kafkaEventMapper.toDomain(event);

            // Llamamos al caso de uso que ya tiene la lógica de "upsert"
            processEnrollmentUseCase.processEnrollmentEvent(enrollmentDomain)
                  .doOnSuccess(result -> log.info("Réplica de la matrícula con ID '{}' guardada/actualizada exitosamente.", result.getId()))
                  .doOnError(error -> log.error("Error al procesar el evento de matrícula con ID '{}': {}", enrollmentDomain.getId(), error.getMessage()))
                  .subscribe(); // ¡Importante! Activa el flujo reactivo.
      }

}