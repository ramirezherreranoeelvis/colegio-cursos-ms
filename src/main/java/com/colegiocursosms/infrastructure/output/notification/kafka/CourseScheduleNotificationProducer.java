package com.colegiocursosms.infrastructure.output.notification.kafka;

import com.colegiocursosms.domain.CourseSchedule;
import com.colegiocursosms.infrastructure.output.notification.dto.CourseScheduleCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CourseScheduleNotificationProducer {

      private final KafkaTemplate<String, Object> kafkaTemplate;

      @Value("${spring.kafka.topics.enrollments-created}")
      private String topicEnrollmentsCreated;

      public void sendEnrollmentCreatedEvent(CourseSchedule courseSchedule, Grade grade, Season season) {
            try {
                  var event = CourseScheduleCreatedEvent.builder()
                        .id(courseSchedule.getId())
                        .build();

                  log.info("Enviando evento simplificado EnrollmentCreatedEvent al tópico '{}' con ID: {}", topicEnrollmentsCreated, event.getId());
                  kafkaTemplate.send(topicEnrollmentsCreated, event);

            } catch (Exception e) {
                  log.error("Error al enviar el evento EnrollmentCreatedEvent para el ID {}: {}", enrollment.getId(), e.getMessage());
            }
      }
}
