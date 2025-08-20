package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.CourseSchedule;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICourseScheduleRepository {

      Mono<CourseSchedule> save(CourseSchedule courseSchedule);

      Mono<CourseSchedule> findById(String id);

      Mono<CourseSchedule> findByCode(String code);

      Mono<Boolean> existsByCode(String code);

      Mono<List<CourseSchedule>> findAll();

}