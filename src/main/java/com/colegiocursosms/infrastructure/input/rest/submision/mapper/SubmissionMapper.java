package com.colegiocursosms.infrastructure.input.rest.submision.mapper;

import com.colegiocursosms.domain.StudentSubmission;
import com.colegiocursosms.infrastructure.input.rest.submision.dto.CreateSubmissionRequest;
import com.colegiocursosms.infrastructure.input.rest.submision.dto.SubmissionResponse;
import org.springframework.stereotype.Component;

@Component
public class SubmissionMapper {

      public StudentSubmission toDomain(CreateSubmissionRequest request) {
            return StudentSubmission.builder()
                  .submissionType(request.getSubmissionType())
                  .submissionContent(request.getSubmissionContent())
                  .build();
      }

      public SubmissionResponse toResponse(StudentSubmission domain) {
            return SubmissionResponse.builder()
                  .id(domain.getId())
                  .studentId(domain.getStudentId())
                  .contentItemId(domain.getContentItemId())
                  .submissionType(domain.getSubmissionType())
                  .submissionContent(domain.getSubmissionContent())
                  .submissionDate(domain.getSubmissionDate())
                  .build();
      }
}