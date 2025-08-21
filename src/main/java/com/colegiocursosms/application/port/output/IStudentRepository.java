package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.Student;
import reactor.core.publisher.Mono;

public interface IStudentRepository {

      Mono<Student> save(Student student);

      Mono<Boolean> existsById(String id);

}