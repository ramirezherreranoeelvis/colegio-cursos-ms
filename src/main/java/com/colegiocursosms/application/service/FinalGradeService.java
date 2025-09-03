package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.grade.IRecordFinalGradeUseCase;
import com.colegiocursosms.application.port.output.IFinalGradeRepository;
import com.colegiocursosms.application.port.output.IStudentRepository;
import com.colegiocursosms.application.port.output.ICourseScheduleRepository;
import com.colegiocursosms.domain.FinalGrade;
import com.colegiocursosms.domain.enums.AuditActionType;
import com.colegiocursosms.domain.exception.FinalGradeAlreadyExistsException;
import com.colegiocursosms.infrastructure.config.AuditingConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@RequiredArgsConstructor
public class FinalGradeService implements IRecordFinalGradeUseCase {

      private final IFinalGradeRepository finalGradeRepository;
      private final IStudentRepository studentRepository;
      private final ICourseScheduleRepository scheduleRepository;

      /**
       * Registra una nota final después de validar que:
       * 1. El estudiante y el horario del curso existan.
       * 2. No exista previamente una nota final para esa combinación.
       */
      @Override
      public Mono<FinalGrade> recordFinalGrade(FinalGrade finalGrade) {
            log.info("Iniciando validación para registrar nota final del alumno {} en el horario {}",
                  finalGrade.getStudentId(), finalGrade.getCourseScheduleId());

            // 1. Validar en paralelo que el estudiante y el horario existan
            Mono<Boolean> studentExists = studentRepository.existsById(finalGrade.getStudentId());
            Mono<Boolean> scheduleExists = scheduleRepository.existsById(finalGrade.getCourseScheduleId());

            return Mono.zip(studentExists, scheduleExists)
                  .flatMap(tuple -> {
                        if (!tuple.getT1()) {
                              return Mono.error(new RuntimeException("El estudiante con ID " + finalGrade.getStudentId() + " no existe."));
                        }
                        if (!tuple.getT2()) {
                              return Mono.error(new RuntimeException("El horario con ID " + finalGrade.getCourseScheduleId() + " no existe."));
                        }
                        // 2. Si ambos existen, validar que la nota final no sea un duplicado
                        return finalGradeRepository.existsByStudentIdAndCourseScheduleIdAndPeriodName(
                              finalGrade.getStudentId(),
                              finalGrade.getCourseScheduleId(),
                              finalGrade.getPeriodName()
                        );
                  })
                  .filter(Boolean.FALSE::equals) // Continuar solo si la nota final NO existe
                  .switchIfEmpty(Mono.error(new FinalGradeAlreadyExistsException(
                        "Ya existe una nota final para este estudiante en este curso y período."
                  )))
                  .then(Mono.defer(() -> {
                        // 3. Si todas las validaciones pasan, guardar la nota
                        log.info("Validaciones correctas. Guardando nota final...");
                        AuditingConfig.setAuditor(AuditActionType.ADMIN.getValue()); // Asumimos que un Admin o Profesor registra la nota
                        return finalGradeRepository.save(finalGrade)
                              .doFinally(signal -> AuditingConfig.clearAuditor());
                  }));
      }
}