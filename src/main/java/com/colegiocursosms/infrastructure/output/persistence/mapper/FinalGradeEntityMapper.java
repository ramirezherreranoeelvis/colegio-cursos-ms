package com.colegiocursosms.infrastructure.output.persistence.mapper;

import com.colegiocursosms.domain.FinalGrade;
import com.colegiocursosms.infrastructure.output.persistence.entity.FinalGradeEntity;
import org.springframework.stereotype.Component;

@Component
public class FinalGradeEntityMapper {

      public FinalGrade toDomain(FinalGradeEntity entity) {
            return FinalGrade.builder()
                  .id(entity.getId())
                  .studentId(entity.getStudentId())
                  .courseScheduleId(entity.getCourseScheduleId())
                  .periodName(entity.getPeriodName())
                  .finalScore(entity.getFinalScore())
                  .createdBy(entity.getCreatedBy())
                  .createdDate(entity.getCreatedDate())
                  .lastModifiedBy(entity.getLastModifiedBy())
                  .lastModifiedDate(entity.getLastModifiedDate())
                  .build();
      }

      public FinalGradeEntity toEntity(FinalGrade domain) {
            return FinalGradeEntity.builder()
                  .id(domain.getId())
                  .studentId(domain.getStudentId())
                  .courseScheduleId(domain.getCourseScheduleId())
                  .periodName(domain.getPeriodName())
                  .finalScore(domain.getFinalScore())
                  .createdBy(domain.getCreatedBy())
                  .createdDate(domain.getCreatedDate())
                  .lastModifiedBy(domain.getLastModifiedBy())
                  .lastModifiedDate(domain.getLastModifiedDate())
                  .build();
      }
}