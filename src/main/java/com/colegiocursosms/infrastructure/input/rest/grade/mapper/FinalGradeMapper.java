package com.colegiocursosms.infrastructure.input.rest.grade.mapper;

import com.colegiocursosms.domain.FinalGrade;
import com.colegiocursosms.infrastructure.input.rest.grade.dto.FinalGradeResponse;
import com.colegiocursosms.infrastructure.input.rest.grade.dto.RecordFinalGradeRequest;
import org.springframework.stereotype.Component;

@Component
public class FinalGradeMapper {

      /**
       * Convierte el DTO de la solicitud a un objeto de dominio.
       * Los campos 'studentId' y 'courseScheduleId' se añadirán en el controlador.
       */
      public FinalGrade toDomain(RecordFinalGradeRequest request) {
            return FinalGrade.builder()
                  .periodName(request.getPeriodName())
                  .finalScore(request.getFinalScore())
                  .build();
      }

      /**
       * Convierte un objeto de dominio a un DTO de respuesta para la API.
       */
      public FinalGradeResponse toResponse(FinalGrade domain) {
            return FinalGradeResponse.builder()
                  .id(domain.getId())
                  .studentId(domain.getStudentId())
                  .courseScheduleId(domain.getCourseScheduleId())
                  .periodName(domain.getPeriodName())
                  .finalScore(domain.getFinalScore())
                  .createdBy(domain.getCreatedBy())
                  .createdDate(domain.getCreatedDate())
                  .build();
      }
}