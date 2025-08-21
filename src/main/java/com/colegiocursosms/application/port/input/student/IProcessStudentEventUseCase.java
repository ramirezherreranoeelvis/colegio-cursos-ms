package com.colegioenrollmentms.application.port.input.student;

import com.colegioenrollmentms.domain.Student;
import reactor.core.publisher.Mono;

public interface IProcessStudentEventUseCase {
      Mono<Student> processStudentCreated(Student student);
}