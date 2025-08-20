package com.colegiocursosms.application.port.input.course;

import com.colegiocursosms.domain.Course;
import reactor.core.publisher.Mono;
public interface IRegisterCoursesUseCase {

     Mono<Course> registerCourse (Course course);
}
