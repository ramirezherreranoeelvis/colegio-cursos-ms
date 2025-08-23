package com.colegiocursosms.application.port.input.teacher;

import com.colegiocursosms.domain.Teacher;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Puerto de entrada para los casos de uso de búsqueda de profesores.
 */
public interface IFindTeachersUseCase {

      Mono<Teacher> findById(String id);

      Mono<List<Teacher>> findAll();

}