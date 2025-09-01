package com.colegiocursosms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FinalGrade extends Auditable {

      private String id;
      private String studentId;
      private String courseScheduleId;
      private String periodName;
      private BigDecimal finalScore;
}