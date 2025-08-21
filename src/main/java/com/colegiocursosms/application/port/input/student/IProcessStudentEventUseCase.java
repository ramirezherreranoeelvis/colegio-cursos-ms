package com.colegiocursosms.application.port.input.student;

import com.colegiocursosms.domain.Student;
import reactor.core.publisher.Mono;

public interface IProcessStudentEventUseCase {
      Mono<Student> processStudentCreated(Student student);
}