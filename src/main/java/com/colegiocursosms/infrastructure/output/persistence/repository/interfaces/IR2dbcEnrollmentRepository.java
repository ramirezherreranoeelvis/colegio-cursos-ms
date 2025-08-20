package com.colegiocursosms.infrastructure.output.persistence.repository.interfaces;

import com.colegiocursosms.infrastructure.output.persistence.entity.EnrollmentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Year;

@Repository
public interface IR2dbcEnrollmentRepository extends ReactiveCrudRepository<EnrollmentEntity, String> {

      Flux<EnrollmentEntity> findAllByYear(Year year);

      Flux<EnrollmentEntity> findAllByGrade(String grade);

      Flux<EnrollmentEntity> findAllByEnrolled(Integer enrolled);

      Mono<Boolean> existsByGradeAndYear(String grade, Year year);

      Mono<EnrollmentEntity> findByGradeAndYear(String grade, Year year);

}