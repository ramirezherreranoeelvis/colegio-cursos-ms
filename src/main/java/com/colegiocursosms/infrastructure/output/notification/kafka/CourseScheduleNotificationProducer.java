package com.colegiocursosms.infrastructure.output.notification.kafka;

import com.colegiocursosms.domain.CourseSchedule;
import com.colegiocursosms.infrastructure.output.notification.mapper.KafkaCourseScheduleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class CourseScheduleNotificationProducer {

      private final KafkaTemplate<String, Object> kafkaTemplate;

      @Value("${spring.kafka.topics.courses-scheduled}")
      private String topicCoursesScheduled;

      private final KafkaCourseScheduleMapper courseScheduleMapper;

      /**
       * Construye y envía un evento de un nuevo curso programado.
       * El método es reactivo porque el mapeo implica una llamada asíncrona a la BD.
       *
       * @param courseSchedule El horario de curso a notificar.
       * @return Un Mono<Void> que se completa cuando el envío se ha iniciado.
       */
      public Mono<Void> sendCourseScheduledEvent(CourseSchedule courseSchedule) {
            // 1. Iniciamos el flujo con la operación asíncrona de mapeo
            return courseScheduleMapper.toCourseScheduleCreatedEvent(courseSchedule)
                  // 2. Si el mapeo es exitoso, ejecutamos este "efecto secundario"
                  .doOnSuccess(event -> {
                        log.info("Enviando evento CourseScheduleCreatedEvent al tópico '{}' con código: {}",
                              topicCoursesScheduled, event.getCode());

                        // Enviamos el evento a Kafka
                        kafkaTemplate.send(topicCoursesScheduled, event);
                  })
                  // 3. Si hubo un error en el mapeo (ej. curso no encontrado)
                  .doOnError(error -> log.error(
                        "Error al construir el evento para el horario con ID {}: {}",
                        courseSchedule.getId(), error.getMessage()
                  ))
                  // 4. Ignoramos el objeto del evento y solo señalamos que el proceso terminó
                  .then();
      }

}
