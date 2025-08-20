package com.colegiocursosms.infrastructure.output.persistence.mapper;

import com.colegiocursosms.domain.Classroom;
import com.colegiocursosms.domain.Course;
import com.colegiocursosms.domain.Enrollment;
import com.colegiocursosms.infrastructure.output.persistence.entity.ClassRoomEntity;
import com.colegiocursosms.infrastructure.output.persistence.entity.CourseEntity;
import com.colegiocursosms.infrastructure.output.persistence.entity.EnrollmentEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

      /**
       * Convierte un objeto de dominio Course a su correspondiente entidad de persistencia.
       */
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

      /**
       * Convierte un objeto de dominio Classroom a su correspondiente entidad de persistencia.
       */
      public ClassRoomEntity toEntity(Classroom classroomDomain) {
            return ClassRoomEntity.builder()
                  .id(classroomDomain.getId())
                  .number(classroomDomain.getNumber())
                  .floor(classroomDomain.getFloor())
                  .createdBy(classroomDomain.getCreatedBy())
                  .createdDate(classroomDomain.getCreatedDate())
                  .lastModifiedBy(classroomDomain.getLastModifiedBy())
                  .lastModifiedDate(classroomDomain.getLastModifiedDate())
                  .build();
      }

      public EnrollmentEntity toEntity(Enrollment enrollmentDomain) {
            return EnrollmentEntity.builder()
                  .id(enrollmentDomain.getId())
                  .enrolled(enrollmentDomain.getEnrolled())
                  .grade(enrollmentDomain.getGrade())
                  .year(enrollmentDomain.getYear())
                  .build();
      }
}