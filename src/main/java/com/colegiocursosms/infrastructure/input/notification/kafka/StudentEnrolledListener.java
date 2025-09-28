package com.colegiocursosms.infrastructure.input.notification.kafka;

import com.colegiocursosms.application.port.input.enrollment.IAssociateStudentToSchedulesUseCase;
import com.colegiocursosms.infrastructure.input.notification.dto.StudentEnrolledEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class StudentEnrolledListener {

      private final IAssociateStudentToSchedulesUseCase associateStudentUseCase;

      @KafkaListener(
            topics = "${spring.kafka.topics.student-enrolled}",
            containerFactory = "studentEnrolledContainerFactory"
      )
      public void listenStudentEnrolled(StudentEnrolledEvent event) {
            log.info("Evento StudentEnrolledEvent recibido. Estudiante ID: {}, Matrícula ID: {}",
                  event.getStudentId(), event.getEnrollmentId());

            // Llamamos al servicio para que ejecute la lógica de negocio
            associateStudentUseCase.associateStudentToSchedules(event.getStudentId(), event.getEnrollmentId())
                  .doOnError(error -> log.error("Error al asociar estudiante {} a los cursos de la matrícula {}: {}",
                        event.getStudentId(), event.getEnrollmentId(), error.getMessage()))
                  .subscribe(); // Se activa el flujo reactivo
      }
}