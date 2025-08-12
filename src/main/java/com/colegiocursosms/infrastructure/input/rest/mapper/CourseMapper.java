package com.colegiocursosms.infrastructure.input.rest.mapper;

import com.colegiocursosms.domain.Course;
import com.colegiocursosms.infrastructure.input.rest.dto.CourseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@AllArgsConstructor
@Component
public class CourseMapper {

      public CourseResponse mapToResponse(Course course) {
            return
                  CourseResponse.builder()
                        .id(course.getId())
                        .name(course.getName())
                        .build()
            ;
      }

}
