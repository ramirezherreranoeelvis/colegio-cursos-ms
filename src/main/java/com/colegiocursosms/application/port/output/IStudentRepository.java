package com.colegioenrollmentms.application.port.output;

import com.colegioenrollmentms.domain.Student;
import reactor.core.publisher.Mono;

public interface IStudentRepository {

      Mono<Student> save(Student student);

      Mono<Boolean> existsById(String id);

}