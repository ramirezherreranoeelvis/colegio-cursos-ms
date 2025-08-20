package com.colegiocursosms.domain;

import com.colegiocursosms.domain.enums.DayOfWeek;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSchedule extends Auditable {

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