package com.colegiocursosms.infrastructure.input.notification.kafka;

import com.colegiocursosms.application.port.input.teacher.IProcessTeacherEventUseCase;
import com.colegiocursosms.infrastructure.input.notification.dto.TeacherCreatedEvent;
import com.colegiocursosms.infrastructure.input.notification.mapper.TeacherNotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class TeacherNotificationConsumer {

      private final IProcessTeacherEventUseCase processTeacherEventUseCase;
      private final TeacherNotificationMapper teacherNotificationMapper;

      @KafkaListener(
            groupId = "teacher-courses-group",
            topics = "${spring.kafka.topics.teacher-created}",
            containerFactory = "teacherContainerFactory"
      )
      public void listenTeacherCreated(TeacherCreatedEvent event) {
            log.info("Evento TeacherCreatedEvent recibido para el profesor con ID: {}", event.getId());

            var teacherDomain = teacherNotificationMapper.toDomain(event);

            processTeacherEventUseCase.processTeacherEvent(teacherDomain)
                  .doOnSuccess(savedTeacher -> log.info("Réplica del profesor con ID '{}' guardada/actualizada.", savedTeacher.getId()))
                  .doOnError(error -> log.error("Error al procesar evento del profesor con ID '{}': {}", teacherDomain.getId(), error.getMessage()))
                  .subscribe();
      }

}