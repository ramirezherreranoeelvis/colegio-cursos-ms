package com.colegiocursosms.application.port.input.course;

import com.colegiocursosms.domain.Course;
import reactor.core.publisher.Mono;

import java.util.List;
public interface IFindCoursesUseCase {

      Mono<List<Course>> findAll();

      /**
       * Busca un curso por su nombre, que se asume es único.
       *
       * @param name El nombre del curso a buscar.
       * @return Un Mono que emite el curso encontrado o se completa vacío si no se encuentra.
       */
      Mono<Course> findByName(String name);

}
