package com.colegiocursosms.application.port.input.grade;

import com.colegiocursosms.domain.FinalGrade;
import reactor.core.publisher.Mono;

/**
 * Puerto de entrada para el caso de uso de registrar una nota final.
 */
public interface IRecordFinalGradeUseCase {

      Mono<FinalGrade> recordFinalGrade(FinalGrade finalGrade);
}