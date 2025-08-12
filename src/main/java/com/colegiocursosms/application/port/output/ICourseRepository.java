package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.Course;
import reactor.core.publisher.Mono;

import java.util.List;
public interface ICourseRepository {

      Mono<Course> save(Course course);

      Mono<Course> findByName(String name);

      Mono<Boolean> existsByName(String name);

      Mono<List<Course>> findAll();

}
