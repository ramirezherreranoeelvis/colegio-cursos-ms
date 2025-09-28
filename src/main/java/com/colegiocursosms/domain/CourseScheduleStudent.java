package com.colegiocursosms.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseScheduleStudent {

      private String id;
      private String courseScheduleId;
      private String studentId;

}