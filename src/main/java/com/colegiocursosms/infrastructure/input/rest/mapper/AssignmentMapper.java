package com.colegiocursosms.infrastructure.input.rest.mapper;

import com.colegiocursosms.domain.CourseScheduleTeacher;
import com.colegiocursosms.infrastructure.input.rest.dto.AssignmentResponse;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {
      public AssignmentResponse toResponse(CourseScheduleTeacher assignment) {
            return AssignmentResponse.builder()
                  .courseScheduleId(assignment.getCourseScheduleId())
                  .teacherId(assignment.getTeacherId())
                  .startDate(assignment.getStartDate())
                  .role(assignment.getRole())
                  .build();
      }
}