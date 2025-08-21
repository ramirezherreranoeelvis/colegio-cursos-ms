package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.IStudentRepository;
import com.colegiocursosms.domain.Student;
import com.colegiocursosms.infrastructure.output.persistence.entity.StudentEntity;
import com.colegiocursosms.infrastructure.output.persistence.mapper.StudentEntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcStudentRepository;
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