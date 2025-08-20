package com.colegiocursosms.application.port.input.enrollment;

import com.colegiocursosms.domain.Enrollment;
import reactor.core.publisher.Mono;

import java.time.Year;
import java.util.List;

/**
 * Puerto de entrada para los casos de uso de búsqueda y consulta de matrículas.
 */
public interface IFindEnrollmentsUseCase {

      Mono<Enrollment> findById(String id);

      Mono<Boolean> existsById(String id);

      Mono<List<Enrollment>> findAll();

      Mono<List<Enrollment>> findAllByYear(Year year);

      Mono<List<Enrollment>> findAllByGrade(String grade);

      Mono<List<Enrollment>> findAllByEnrolled(Integer enrolled);

      Mono<Boolean> existsByGradeAndYear(String grade, Year year);

      Mono<Enrollment> findByGradeAndYear(String grade, Year year);
}