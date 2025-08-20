package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.Enrollment;
import reactor.core.publisher.Mono;

import java.time.Year;
import java.util.List;

/**
 * Puerto de salida que define el contrato para las operaciones de
 * persistencia de la entidad Enrollment.
 */
public interface IEnrollmentRepository {

      Mono<Enrollment> save(Enrollment enrollment);

      Mono<Enrollment> update(Enrollment enrollment);

      Mono<Enrollment> findById(String id);

      Mono<Boolean> existsById(String id);

      Mono<List<Enrollment>> findAll();

      Mono<List<Enrollment>> findAllByYear(Year year);

      Mono<List<Enrollment>> findAllByGrade(String grade);

      Mono<List<Enrollment>> findAllByEnrolled(Integer enrolled);

      Mono<Boolean> existsByGradeAndYear(String grade, Year year);

      Mono<Enrollment> findByGradeAndYear(String grade, Year year);

}