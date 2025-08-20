package com.colegiocursosms.application.port.input.classroom;

import com.colegiocursosms.domain.Classroom;
import reactor.core.publisher.Mono;

/**
 * Puerto de entrada para el caso de uso de registrar una nueva aula.
 */
public interface IRegisterClassroomUseCase {

      Mono<Classroom> registerClassroom(Classroom classroom);

}