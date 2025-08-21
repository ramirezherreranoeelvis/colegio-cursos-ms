package com.colegiocursosms.infrastructure.output.notification.mapper;

import com.colegiocursosms.application.port.input.course.IFindCoursesUseCase;
import com.colegiocursosms.domain.CourseSchedule;
import com.colegiocursosms.infrastructure.output.notification.dto.CourseScheduleCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Mapper responsable de crear los DTOs para los eventos de Kafka.
 * Puede enriquecer los datos realizando búsquedas a través de los casos de uso.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class KafkaCourseScheduleMapper {

      private final IFindCoursesUseCase findCourseUseCase;

      /**
       * Convierte un objeto de dominio CourseSchedule a un DTO de evento CourseScheduleCreatedEvent.
       * Este proceso enriquece el objeto buscando el nombre del curso correspondiente al courseId.
       *
       * @param scheduleDomain El horario de curso del dominio.
       * @return Un Mono que emite el DTO del evento enriquecido.
       */
      public Mono<CourseScheduleCreatedEvent> toCourseScheduleCreatedEvent(CourseSchedule scheduleDomain) {
            // 1. Buscamos el curso usando el courseId del horario
            return findCourseUseCase.findById(scheduleDomain.getCourseId())
                  // 2. Si no se encuentra, lanzamos un error para detener el proceso
                  .switchIfEmpty(Mono.error(
                        new RuntimeException("Inconsistencia de datos: No se encontró el curso con ID " + scheduleDomain.getCourseId())
                  ))
                  // 3. Si se encuentra, usamos el curso y el horario para construir el evento
                  .map(course -> CourseScheduleCreatedEvent.builder()
                        .id(scheduleDomain.getId())
                        .courseName(course.getName()) // <-- Dato enriquecido
                        .day(scheduleDomain.getDay())
                        .startTime(scheduleDomain.getStartTime())
                        .endTime(scheduleDomain.getEndTime())
                        .classroomNumber(scheduleDomain.getClassroomNumber())
                        .classroomFloor(scheduleDomain.getClassroomFloor())
                        .enrollmentId(scheduleDomain.getEnrollmentId())
                        .code(scheduleDomain.getCode())
                        .build());
      }

}