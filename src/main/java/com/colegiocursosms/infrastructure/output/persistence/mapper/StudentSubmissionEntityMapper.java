package com.colegiocursosms.infrastructure.output.persistence.mapper;

import com.colegiocursosms.domain.StudentSubmission;
import com.colegiocursosms.infrastructure.output.persistence.entity.StudentSubmissionEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentSubmissionEntityMapper {

      public StudentSubmission toDomain(StudentSubmissionEntity entity) {
            return StudentSubmission.builder()
                  .id(entity.getId())
                  .studentId(entity.getStudentId())
                  .contentItemId(entity.getContentItemId())
                  .submissionType(entity.getSubmissionType())
                  .submissionContent(entity.getSubmissionContent())
                  .submissionDate(entity.getSubmissionDate())
                  .build();
      }

      public StudentSubmissionEntity toEntity(StudentSubmission domain) {
            return StudentSubmissionEntity.builder()
                  .id(domain.getId())
                  .studentId(domain.getStudentId())
                  .contentItemId(domain.getContentItemId())
                  .submissionType(domain.getSubmissionType())
                  .submissionContent(domain.getSubmissionContent())
                  .submissionDate(domain.getSubmissionDate())
                  .build();
      }
}