package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.CourseSchedule;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICourseScheduleRepository {

      Mono<CourseSchedule> save(CourseSchedule courseSchedule);

      Mono<CourseSchedule> findById(String id);

      Mono<Boolean> existsById(String id);

      Mono<CourseSchedule> findByCode(String code);

      Mono<Boolean> existsByCode(String code);

      Mono<List<CourseSchedule>> findAll();

      /**
       * Busca todos los horarios de curso asociados a un ID de matrícula específico.
       * @param enrollmentId El ID de la matrícula.
       * @return Un Mono que emite una lista de los horarios encontrados.
       */
      Mono<List<CourseSchedule>> findAllByEnrollmentId(String enrollmentId);
}