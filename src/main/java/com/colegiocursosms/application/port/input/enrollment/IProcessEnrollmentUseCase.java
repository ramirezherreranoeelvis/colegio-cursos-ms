package com.colegiocursosms.application.port.input;

import com.colegiocursosms.domain.Enrollment;
import reactor.core.publisher.Mono;

/**
 * Puerto de entrada para el caso de uso de procesar un evento de matrícula
 * (creación o actualización de la réplica local).
 */
public interface IProcessEnrollmentUseCase {
      Mono<Enrollment> processEnrollmentEvent(Enrollment enrollment);
      Mono<Enrollment> saveEnrollment(Enrollment enrollment);
      Mono<Enrollment> updateEnrollment(Enrollment enrollment);

}