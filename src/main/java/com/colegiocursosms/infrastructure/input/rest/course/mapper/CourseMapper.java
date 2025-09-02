package com.colegiocursosms.infrastructure.input.rest.mapper;

import com.colegiocursosms.domain.Course;
import com.colegiocursosms.infrastructure.input.rest.dto.CourseResponse;
import com.colegiocursosms.infrastructure.input.rest.dto.RegisterCourseRequest;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

      /**
       * Convierte un objeto de dominio Course a un DTO de respuesta.
       */
      public CourseResponse mapToResponse(Course course) {
            return CourseResponse.builder()
                  .id(course.getId())
                  .name(course.getName())
                  .build();
      }

      /**
       * Convierte un DTO de solicitud RegisterCourseRequest a un objeto de dominio Course.
       */
      public Course toDomain(RegisterCourseRequest request) {
            return Course.builder()
                  .name(request.getName())
                  .build();
      }
}