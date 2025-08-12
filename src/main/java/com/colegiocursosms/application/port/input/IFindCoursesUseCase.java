package com.colegiocursosms.application.port.input;

import com.colegiocursosms.domain.Course;
import reactor.core.publisher.Mono;

import java.util.List;
public interface IFindCoursesUseCase {
      Mono<List<Course>> findAll();
}
