package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.CourseScheduleStudent;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida que define el contrato para persistir la relación
 * entre un estudiante y un horario de curso.
 */
public interface ICourseScheduleStudentRepository {

      /**
       * Guarda una nueva asociación entre un estudiante y un horario.
       */
      Mono<CourseScheduleStudent> save(CourseScheduleStudent association);

}