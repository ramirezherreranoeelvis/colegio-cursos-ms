package com.colegiocursosms.infrastructure.input.rest.grade;

import com.colegiocursosms.application.port.input.grade.IRecordFinalGradeUseCase;
import com.colegiocursosms.domain.FinalGrade;
import com.colegiocursosms.infrastructure.input.rest.grade.dto.FinalGradeResponse;
import com.colegiocursosms.infrastructure.input.rest.grade.dto.RecordFinalGradeRequest;
import com.colegiocursosms.infrastructure.input.rest.grade.mapper.FinalGradeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/course-schedules/{scheduleId}/students/{studentId}/final-grades")
@RequiredArgsConstructor
public class FinalGradeController {

      private final IRecordFinalGradeUseCase recordFinalGradeUseCase;
      private final FinalGradeMapper mapper; // Necesitaremos crear este mapper

      /**
       * Endpoint para registrar la nota final de un estudiante en un curso para un período.
       */
      @PostMapping("")
      public Mono<ResponseEntity<FinalGradeResponse>> recordFinalGrade(
            @PathVariable String scheduleId,
            @PathVariable String studentId,
            @Valid @RequestBody RecordFinalGradeRequest request) {

            // 1. Convertimos el DTO a un objeto de dominio
            FinalGrade newGrade = mapper.toDomain(request);
            // 2. Enriquecemos el objeto con los IDs de la URL
            newGrade.setCourseScheduleId(scheduleId);
            newGrade.setStudentId(studentId);

            // 3. Llamamos al caso de uso para que aplique la lógica de negocio
            return recordFinalGradeUseCase.recordFinalGrade(newGrade)
                  .map(mapper::toResponse)
                  .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
      }
}