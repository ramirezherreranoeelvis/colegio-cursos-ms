package com.colegiocursosms.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class CourseScheduleTeacher extends Auditable {

      private String id;
      private String courseScheduleId;
      private String teacherId;
      private LocalDate startDate;
      private LocalDate endDate;
      private String role;

}