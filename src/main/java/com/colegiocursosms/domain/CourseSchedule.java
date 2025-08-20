package com.colegiocursosms.domain;

import com.colegiocursosms.domain.enums.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSchedule {

      private String id;
      private String code;
      private String enrollmentId;
      private String courseId;
      private DayOfWeek day;
      private LocalTime startTime;
      private LocalTime endTime;
      private Integer classroomNumber;
      private Integer classroomFloor;
      private String portada;

}