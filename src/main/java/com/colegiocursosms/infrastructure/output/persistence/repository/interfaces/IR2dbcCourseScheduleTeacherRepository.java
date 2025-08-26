package com.colegiocursosms.infrastructure.output.persistence.repository.interfaces;

import com.colegiocursosms.infrastructure.output.persistence.entity.CourseScheduleTeacherEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IR2dbcCourseScheduleTeacherRepository extends ReactiveCrudRepository<CourseScheduleTeacherEntity, String> {

      /**
       * Busca todas las asignaciones de profesores para un horario de curso específico.
       */
      Flux<CourseScheduleTeacherEntity> findAllByCourseScheduleId(String courseScheduleId);

      Mono<Boolean> existsByCourseScheduleIdAndTeacherId(String courseScheduleId, String teacherId);

}