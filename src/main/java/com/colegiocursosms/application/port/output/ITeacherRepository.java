package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.Teacher;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITeacherRepository {

      Mono<Teacher> insert(Teacher teacher);

      Mono<Teacher> update(Teacher teacher);

      Mono<Teacher> findById(String id);

      Mono<Boolean> existsById(String id);

      Mono<List<Teacher>> findAll();

}