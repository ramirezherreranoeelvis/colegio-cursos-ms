package com.colegiocursosms.infrastructure.output.notification.dto;

import com.colegiocursosms.domain.enums.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseScheduleCreatedEvent {

      private String id;
      private String courseName;
      private DayOfWeek day;
      private java.time.LocalTime startTime;
      private java.time.LocalTime endTime;
      private Integer classroomNumber;
      private Integer classroomFloor;
      private String enrollmentId;
      private String code;

}
