package com.colegiocursosms.infrastructure.output.persistence.repository.interfaces;

import com.colegiocursosms.infrastructure.output.persistence.entity.CourseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IR2dbcCoursesRepository extends ReactiveCrudRepository<CourseEntity, String> {

      Mono<Boolean> existsByName(String name);

      Mono<CourseEntity> findByName(String name);

}
