package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.IFindCoursesUseCase;
import com.colegiocursosms.application.port.input.IRegisterCoursesUseCase;
import com.colegiocursosms.application.port.output.ICourseRepository;
import com.colegiocursosms.domain.Course;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class CoursesService implements IRegisterCoursesUseCase, IFindCoursesUseCase {

      private final ICourseRepository courseRepository;

      @Override
      public Mono<Course> registerCourse(Course course) {
            log.info("Registrando un curso");
            return null;
      }

      @Override
      public Mono<List<Course>> findAll() {
            return this.courseRepository.findAll();
      }

}
