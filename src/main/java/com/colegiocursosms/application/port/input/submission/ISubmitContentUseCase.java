package com.colegiocursosms.application.port.input.submission;

import com.colegiocursosms.domain.StudentSubmission;
import reactor.core.publisher.Mono;

public interface ISubmitContentUseCase {
      /**
       * Procesa la entrega de un trabajo por parte de un estudiante,
       * aplicando las validaciones de negocio necesarias.
       */
      Mono<StudentSubmission> submitContent(StudentSubmission submission);
}