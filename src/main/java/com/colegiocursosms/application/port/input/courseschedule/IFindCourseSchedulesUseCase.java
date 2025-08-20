package com.colegiocursosms.application.port.input.courseschedule;

import com.colegiocursosms.domain.CourseSchedule;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IFindCourseSchedulesUseCase {

      Mono<CourseSchedule> findById(String id);

      Mono<CourseSchedule> findByCode(String code);

      Mono<List<CourseSchedule>> findAll();

}