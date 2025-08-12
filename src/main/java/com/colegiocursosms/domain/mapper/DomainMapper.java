package com.colegiocursosms.domain.mapper;

import com.colegiocursosms.domain.Course;
import com.colegiocursosms.infrastructure.output.persistence.entity.CourseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.colegiocursosms.domain.Classroom;
import com.colegiocursosms.infrastructure.output.persistence.entity.ClassRoomEntity;

@Component
public class DomainMapper {

      /**
       * Convierte una entidad de persistencia CourseEntity a un objeto de dominio Course.
       */
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

      /**
       * Convierte una entidad de persistencia ClassRoomEntity a un objeto de dominio Classroom.
       */
      public Mono<Classroom> toDomain(ClassRoomEntity classroomEntity) {
            return Mono.just(
                  Classroom.builder()
                        .id(classroomEntity.getId())
                        .number(classroomEntity.getNumber())
                        .floor(classroomEntity.getFloor())
                        .createdBy(classroomEntity.getCreatedBy())
                        .createdDate(classroomEntity.getCreatedDate())
                        .lastModifiedBy(classroomEntity.getLastModifiedBy())
                        .lastModifiedDate(classroomEntity.getLastModifiedDate())
                        .build()
            );
      }

}
