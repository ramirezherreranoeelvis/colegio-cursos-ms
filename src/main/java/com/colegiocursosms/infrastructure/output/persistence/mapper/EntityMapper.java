package com.colegiocursosms.infrastructure.output.persistence.mapper;

import com.colegiocursosms.domain.Course;
import com.colegiocursosms.infrastructure.output.persistence.entity.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

      public CourseEntity toEntity(Course courseDomain) {
            return CourseEntity.builder()
                  .id(courseDomain.getId())
                  .name(courseDomain.getName())
                  .createdBy(courseDomain.getCreatedBy())
                  .createdDate(courseDomain.getCreatedDate())
                  .lastModifiedBy(courseDomain.getLastModifiedBy())
                  .lastModifiedDate(courseDomain.getLastModifiedDate())
                  .build();
      }
}
