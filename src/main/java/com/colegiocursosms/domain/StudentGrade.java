package com.colegiocursosms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StudentGrade extends Auditable {

      private String id;
      private String studentId;
      private String contentItemId;
      private BigDecimal score;
      private Instant submissionDate;
      private String comments;
}