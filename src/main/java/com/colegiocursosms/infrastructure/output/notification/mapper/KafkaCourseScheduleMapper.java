package com.colegiocursosms.infrastructure.output.notification.mapper;

import com.colegiocursosms.application.port.input.classroom.IFindClassroomsUseCase;
import com.colegiocursosms.application.port.input.course.IFindCoursesUseCase;
import com.colegiocursosms.domain.Classroom;
import com.colegiocursosms.domain.Course;
import com.colegiocursosms.domain.CourseSchedule;
import com.colegiocursosms.infrastructure.output.notification.dto.CourseScheduleCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Mapper responsable de crear los DTOs para los eventos de Kafka.
 * Enriquece los datos realizando búsquedas a través de los casos de uso.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class KafkaCourseScheduleMapper {

      private final IFindCoursesUseCase findCourseUseCase;
      private final IFindClassroomsUseCase findClassroomsUseCase; // <-- Nueva dependencia

      /**
       * Convierte y enriquece un CourseSchedule a un CourseScheduleCreatedEvent.
       * Busca de forma asíncrona el nombre del curso y los detalles del aula.
       */
      public Mono<CourseScheduleCreatedEvent> toCourseScheduleCreatedEvent(CourseSchedule scheduleDomain) {
            // 1. Preparamos las dos búsquedas asíncronas que necesitamos
            Mono<Course> courseMono = findCourseUseCase.findById(scheduleDomain.getCourseId())
                  .switchIfEmpty(Mono.error(new RuntimeException("Inconsistencia: No se encontró Curso con ID " + scheduleDomain.getCourseId())));

            Mono<Classroom> classroomMono = findClassroomsUseCase.findById(scheduleDomain.getIdClassroom())
                  .switchIfEmpty(Mono.error(new RuntimeException("Inconsistencia: No se encontró Aula con ID " + scheduleDomain.getIdClassroom())));

            // 2. Usamos Mono.zip para ejecutarlas en paralelo
            return Mono.zip(courseMono, classroomMono)
                  // 3. Cuando ambas terminen, mapemos el resultado (una Tupla) al DTO final
                  .map(tuple -> {
                        Course course = tuple.getT1();
                        Classroom classroom = tuple.getT2();

                        // 4. Construimos el evento con los datos de las 3 fuentes
                        return CourseScheduleCreatedEvent.builder()
                              .id(scheduleDomain.getId())
                              .code(scheduleDomain.getCode())
                              .enrollmentId(scheduleDomain.getEnrollmentId())
                              .courseName(course.getName())
                              .classroomNumber(classroom.getNumber())
                              .classroomFloor(classroom.getFloor())
                              .day(scheduleDomain.getDay())
                              .startTime(scheduleDomain.getStartTime())
                              .endTime(scheduleDomain.getEndTime())
                              .build();
                  });
      }

}