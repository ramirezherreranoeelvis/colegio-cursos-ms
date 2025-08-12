package com.colegiocursosms.domain.mapper;

import com.colegiocursosms.domain.Course;
import com.colegiocursosms.infrastructure.output.persistence.entity.CourseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DomainMapper {

      public Mono<Course> toDomain(CourseEntity courseEntity) {
            return Mono.just(
                  Course.builder()
                        .id(courseEntity.getId())
                        .name(courseEntity.getName())
                        .createdBy(courseEntity.getCreatedBy())
                        .createdDate(courseEntity.getCreatedDate())
                        .lastModifiedBy(courseEntity.getLastModifiedBy())
                        .lastModifiedDate(courseEntity.getLastModifiedDate())
                        .build()
            );
      }

}
