package com.colegiocursosms.domain;

import com.colegiocursosms.domain.enums.SubmissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubmission {

      private String id;
      private String studentId;
      private String contentItemId;
      private SubmissionType submissionType;
      private String submissionContent; // JSON con el contenido
      private Instant submissionDate;
}