package com.colegioenrollmentms.infrastructure.output.persistence.repository;

import com.colegioenrollmentms.application.port.output.IStudentRepository;
import com.colegioenrollmentms.domain.Student;
import com.colegioenrollmentms.infrastructure.output.persistence.entity.StudentEntity;
import com.colegioenrollmentms.infrastructure.output.persistence.mapper.StudentEntityMapper;
import com.colegioenrollmentms.infrastructure.output.persistence.repository.interfaces.IR2dbcStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class R2dbcStudentRepository implements IStudentRepository {

      private final IR2dbcStudentRepository r2dbcRepository;
      private final StudentEntityMapper mapper;

      @Override
      public Mono<Student> save(Student student) {
            StudentEntity entity = mapper.toEntity(student);
            entity.markNew(); // Para forzar un INSERT
            return r2dbcRepository.save(entity)
                  .map(mapper::toDomain);
      }

      @Override
      public Mono<Boolean> existsById(String id) {
            return r2dbcRepository.existsById(id);
      }
}