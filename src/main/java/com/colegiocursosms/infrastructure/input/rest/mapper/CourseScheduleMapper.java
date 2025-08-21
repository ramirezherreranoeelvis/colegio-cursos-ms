package com.colegiocursosms.infrastructure.input.rest.mapper;

import com.colegiocursosms.domain.CourseSchedule;
import com.colegiocursosms.infrastructure.input.rest.dto.CourseScheduleResponse;
import com.colegiocursosms.infrastructure.input.rest.dto.ScheduleCourseRequest;
import org.springframework.stereotype.Component;

/**
 * Mapper responsable de las conversiones entre DTOs y objetos
 * de dominio para la entidad CourseSchedule.
 */
@Component
public class CourseScheduleMapper {

      public CourseSchedule toDomain(ScheduleCourseRequest request) {
            return CourseSchedule.builder()
                  .code(request.getCode())
                  .enrollmentId(request.getEnrollmentId())
                  .courseId(request.getCourseId())
                  .day(request.getDay())
                  .startTime(request.getStartTime())
                  .endTime(request.getEndTime())
                  .classroomNumber(request.getClassroomNumber())
                  .classroomFloor(request.getClassroomFloor())
                  .portada(request.getPortada())
                  .build();
      }

      public CourseScheduleResponse toResponse(CourseSchedule domain) {
            return CourseScheduleResponse.builder()
                  .id(domain.getId())
                  .code(domain.getCode())
                  .enrollmentId(domain.getEnrollmentId())
                  .courseId(domain.getCourseId())
                  .day(domain.getDay())
                  .startTime(domain.getStartTime())
                  .endTime(domain.getEndTime())
                  .classroomNumber(domain.getClassroomNumber())
                  .classroomFloor(domain.getClassroomFloor())
                  .portada(domain.getPortada())
                  .build();
      }
}