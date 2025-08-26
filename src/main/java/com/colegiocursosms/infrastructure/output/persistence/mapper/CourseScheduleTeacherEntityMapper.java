package com.colegiocursosms.infrastructure.output.persistence.mapper;

import com.colegiocursosms.domain.CourseScheduleTeacher;
import com.colegiocursosms.infrastructure.output.persistence.entity.CourseScheduleTeacherEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseScheduleTeacherEntityMapper {

      /**
       * Convierte una entidad de persistencia a un objeto de dominio.
       */
      public CourseScheduleTeacher toDomain(CourseScheduleTeacherEntity entity) {
            return CourseScheduleTeacher.builder()
                  .id(entity.getId())
                  .courseScheduleId(entity.getCourseScheduleId())
                  .teacherId(entity.getTeacherId())
                  .startDate(entity.getStartDate())
                  .endDate(entity.getEndDate())
                  .role(entity.getRole())
                  .createdBy(entity.getCreatedBy())
                  .createdDate(entity.getCreatedDate())
                  .lastModifiedBy(entity.getLastModifiedBy())
                  .lastModifiedDate(entity.getLastModifiedDate())
                  .build();
      }

      /**
       * Convierte un objeto de dominio a su correspondiente entidad de persistencia.
       */
      public CourseScheduleTeacherEntity toEntity(CourseScheduleTeacher domain) {
            return CourseScheduleTeacherEntity.builder()
                  .id(domain.getId())
                  .courseScheduleId(domain.getCourseScheduleId())
                  .teacherId(domain.getTeacherId())
                  .startDate(domain.getStartDate())
                  .endDate(domain.getEndDate())
                  .role(domain.getRole())
                  .createdBy(domain.getCreatedBy())
                  .createdDate(domain.getCreatedDate())
                  .lastModifiedBy(domain.getLastModifiedBy())
                  .lastModifiedDate(domain.getLastModifiedDate())
                  .build();
      }

}