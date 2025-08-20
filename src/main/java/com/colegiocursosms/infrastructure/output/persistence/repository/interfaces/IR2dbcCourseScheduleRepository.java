package com.colegiocursosms.infrastructure.output.persistence.repository.interfaces;

import com.colegiocursosms.infrastructure.output.persistence.entity.CourseScheduleEntity;
import io.r2dbc.spi.Result;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IR2dbcCourseScheduleRepository extends ReactiveCrudRepository<CourseScheduleEntity, String> {

      Mono<Boolean> existsByCode(String code);


      Mono<CourseScheduleEntity> findByCode(String code);

}