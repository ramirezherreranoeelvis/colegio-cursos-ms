package com.colegiocursosms.application.port.input.classroom;

import com.colegiocursosms.domain.Classroom;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * Puerto de entrada para el caso de uso de buscar todas las aulas.
 */
public interface IFindClassroomsUseCase {

      Mono<List<Classroom>> findAll();
      Mono<Classroom> findById(String id);
}