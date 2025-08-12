package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.Classroom;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * Puerto de salida que define el contrato para las operaciones de
 * persistencia de la entidad Classroom.
 */
public interface IClassroomRepository {

      Mono<Classroom> save(Classroom classroom);

      Mono<Boolean> existsByNumber(Integer number);

      Mono<Classroom> findByNumber(Integer number);

      Mono<List<Classroom>> findAll();
}