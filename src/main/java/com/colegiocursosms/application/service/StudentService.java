package com.colegioenrollmentms.application.service;

import com.colegioenrollmentms.application.port.input.student.IProcessStudentEventUseCase;
import com.colegioenrollmentms.application.port.output.IStudentRepository;
import com.colegioenrollmentms.domain.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@RequiredArgsConstructor
public class StudentService implements IProcessStudentEventUseCase {

      private final IStudentRepository studentRepository;

      @Override
      public Mono<Student> processStudentCreated(Student student) {
            log.info("Procesando evento para crear réplica del estudiante con ID: {}", student.getId());
            return studentRepository.existsById(student.getId())
                  .filter(exists -> !exists)
                  .switchIfEmpty(Mono.defer(() -> {
                        log.warn("La réplica del estudiante con ID '{}' ya existe. Se omite el guardado.", student.getId());
                        return Mono.empty();
                  }))
                  .then(studentRepository.save(student));
      }

}