package com.colegiocursosms.application.port.input.enrollment;

import reactor.core.publisher.Mono;

/**
 * Puerto de entrada para el caso de uso que asocia a un estudiante
 * con todos los horarios de curso correspondientes a su matrícula.
 */
public interface IAssociateStudentToSchedulesUseCase {

      /**
       * Procesa la asociación de un estudiante a sus horarios de curso.
       * @param studentId El ID del estudiante que se matriculó.
       * @param enrollmentId El ID de la matrícula.
       * @return Un Mono<Void> que se completa cuando todas las asociaciones se han guardado.
       */
      Mono<Void> associateStudentToSchedules(String studentId, String enrollmentId);
}