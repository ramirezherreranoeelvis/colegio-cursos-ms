package com.colegiocursosms.infrastructure.output.persistence.mapper;

import com.colegiocursosms.domain.CourseScheduleStudent;
import com.colegiocursosms.infrastructure.output.persistence.entity.CourseScheduleStudentEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseScheduleStudentEntityMapper {

      public CourseScheduleStudent toDomain(CourseScheduleStudentEntity entity) {
            return CourseScheduleStudent.builder()
                  .id(entity.getId()) // <-- Añadido
                  .courseScheduleId(entity.getCourseScheduleId())
                  .studentId(entity.getStudentId())
                  .build();
      }

      public CourseScheduleStudentEntity toEntity(CourseScheduleStudent domain) {
            return CourseScheduleStudentEntity.builder()
                  .id(domain.getId()) // <-- Añadido
                  .courseScheduleId(domain.getCourseScheduleId())
                  .studentId(domain.getStudentId())
                  .build();
      }
}