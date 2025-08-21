package com.colegioenrollmentms.infrastructure.input.notification;

import com.colegioenrollmentms.application.port.input.student.IProcessStudentEventUseCase;
import com.colegioenrollmentms.infrastructure.input.notification.dto.CourseScheduleEvent;
import com.colegioenrollmentms.infrastructure.input.notification.dto.StudentCreatedEvent;
import com.colegioenrollmentms.infrastructure.input.notification.mapper.StudentNotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class StudentNotificationConsumer {

      private final IProcessStudentEventUseCase processStudentEventUseCase;
      private final StudentNotificationMapper studentNotificationMapper;

      @KafkaListener(
            groupId = "student-auth",
            topics = "${spring.kafka.topics.students-created}", // Asegúrate de añadir 'students-created' a tu application.yml
            containerFactory = "studentContainerFactory"
      )
      public void listenStudentCreated(StudentCreatedEvent event) {
            log.info("Evento StudentCreatedEvent recibido para el estudiante con DNI: {}", event.getDni());

            var studentDomain = studentNotificationMapper.toDomain(event);

            processStudentEventUseCase.processStudentCreated(studentDomain)
                  .doOnSuccess(savedStudent -> log.info("Réplica del estudiante con ID '{}' guardada exitosamente.", savedStudent.getId()))
                  .doOnError(error -> log.error("Error al procesar el evento del estudiante con ID '{}': {}", studentDomain.getId(), error.getMessage()))
                  .subscribe();
      }


}
