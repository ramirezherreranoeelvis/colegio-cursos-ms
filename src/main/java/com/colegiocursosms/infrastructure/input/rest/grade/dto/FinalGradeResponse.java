package com.colegiocursosms.infrastructure.input.rest.grade.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class FinalGradeResponse {
      private String id;
      private String studentId;
      private String courseScheduleId;
      private String periodName;
      private BigDecimal finalScore;
      private String createdBy;
      private Instant createdDate;
}