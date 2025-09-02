package com.colegiocursosms.application.port.input.courseschedule;

import com.colegiocursosms.domain.CourseSchedule;
import reactor.core.publisher.Mono;

public interface IScheduleCourseUseCase {

     Mono<CourseSchedule> registerCourse (CourseSchedule course);
}
