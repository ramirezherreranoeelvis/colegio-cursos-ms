package com.colegiocursosms.application.port.input.teacher;

import com.colegiocursosms.domain.Teacher;
import reactor.core.publisher.Mono;

/**
 * Puerto de entrada para el caso de uso de procesar un evento de profesor
 * (creación o actualización de la réplica local).
 */
public interface IProcessTeacherEventUseCase {

      Mono<Teacher> processTeacherEvent(Teacher teacher);

}