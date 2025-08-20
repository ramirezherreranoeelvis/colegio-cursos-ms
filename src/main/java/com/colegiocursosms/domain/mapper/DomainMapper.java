package com.colegiocursosms.domain.mapper;

import com.colegiocursosms.domain.Course;
import com.colegiocursosms.domain.Enrollment;
import com.colegiocursosms.infrastructure.output.persistence.entity.CourseEntity;
import com.colegiocursosms.infrastructure.output.persistence.entity.EnrollmentEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.colegiocursosms.domain.Classroom;
import com.colegiocursosms.infrastructure.output.persistence.entity.ClassRoomEntity;
import com.colegiocursosms.domain.CourseSchedule;
import com.colegiocursosms.infrastructure.output.persistence.entity.CourseScheduleEntity;

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

      /**
       * Convierte una entidad de persistencia EnrollmentEntity a un objeto de dominio Enrollment.
       */
      public Mono<Enrollment> toDomain(EnrollmentEntity enrollmentEntity) {
            return Mono.just(
                  Enrollment.builder()
                        .id(enrollmentEntity.getId())
                        .enrolled(enrollmentEntity.getEnrolled())
                        .grade(enrollmentEntity.getGrade())
                        .year(enrollmentEntity.getYear())
                        .build()
            );
      }

      public Mono<CourseSchedule> toDomain(CourseScheduleEntity entity) {
            return Mono.just(
                  CourseSchedule.builder()
                        .id(entity.getId())
                        .code(entity.getCode())
                        .enrollmentId(entity.getEnrollmentId())
                        .courseId(entity.getCourseId())
                        .day(entity.getDay())
                        .startTime(entity.getStartTime())
                        .endTime(entity.getEndTime())
                        .classroomNumber(entity.getClassroomNumber())
                        .classroomFloor(entity.getClassroomFloor())
                        .portada(entity.getPortada())
                        .createdBy(entity.getCreatedBy())
                        .createdDate(entity.getCreatedDate())
                        .lastModifiedBy(entity.getLastModifiedBy())
                        .lastModifiedDate(entity.getLastModifiedDate())
                        .build()
            );
      }

}
