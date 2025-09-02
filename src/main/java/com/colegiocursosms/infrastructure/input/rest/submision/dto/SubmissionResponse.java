package com.colegiocursosms.infrastructure.input.rest.submision.dto;

import com.colegiocursosms.domain.enums.SubmissionType;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class SubmissionResponse {
      private String id;
      private String studentId;
      private String contentItemId;
      private SubmissionType submissionType;
      private String submissionContent;
      private Instant submissionDate;
}