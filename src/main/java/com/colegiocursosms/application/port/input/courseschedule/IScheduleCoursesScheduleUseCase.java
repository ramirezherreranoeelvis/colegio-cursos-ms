package com.colegiocursosms.application.port.input.courseschedule;

import com.colegiocursosms.domain.CourseSchedule;
import reactor.core.publisher.Mono;

public interface IRegisterCoursesScheduleUseCase {

     Mono<CourseSchedule> registerCourse (CourseSchedule course);
}
