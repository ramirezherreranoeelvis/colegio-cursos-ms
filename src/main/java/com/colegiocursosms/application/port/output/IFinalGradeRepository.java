package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.FinalGrade;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida que define el contrato para las operaciones de
 * persistencia de la entidad FinalGrade.
 */
public interface IFinalGradeRepository {

      Mono<FinalGrade> save(FinalGrade finalGrade);

      Mono<Boolean> existsByStudentIdAndCourseScheduleIdAndPeriodName(String studentId, String scheduleId, String periodName);
}