package com.colegiocursosms.infrastructure.output.persistence.repository.interfaces;

import com.colegiocursosms.infrastructure.output.persistence.entity.FinalGradeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IR2dbcFinalGradeRepository extends ReactiveCrudRepository<FinalGradeEntity, String> {

      /**
       * Verifica si ya existe una nota final para un estudiante en un curso y período específicos.
       * Esto es crucial para evitar duplicados.
       */
      Mono<Boolean> existsByStudentIdAndCourseScheduleIdAndPeriodName(String studentId, String courseScheduleId, String periodName);
}