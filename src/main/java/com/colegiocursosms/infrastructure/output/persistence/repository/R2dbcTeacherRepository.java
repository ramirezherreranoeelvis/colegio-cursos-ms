package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.ITeacherRepository;
import com.colegiocursosms.domain.Teacher;
import com.colegiocursosms.infrastructure.output.persistence.mapper.TeacherEntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class R2dbcTeacherRepository implements ITeacherRepository {

      private final IR2dbcTeacherRepository r2dbcRepository;
      private final TeacherEntityMapper mapper;

      @Override
      public Mono<Teacher> insert(Teacher teacher) {
            var entity = mapper.toEntity(teacher);
            entity.markNew();
            return r2dbcRepository.save(entity)
                  .map(mapper::toDomain);
      }

      @Override
      public Mono<Teacher> update(Teacher teacher) {
            var entity = mapper.toEntity(teacher);
            return r2dbcRepository.save(entity)
                  .map(mapper::toDomain);
      }

      @Override
      public Mono<Teacher> findById(String id) {
            return r2dbcRepository.findById(id)
                  .map(mapper::toDomain);
      }

      @Override
      public Mono<Boolean> existsById(String id) {
            return r2dbcRepository.existsById(id);
      }

      @Override
      public Mono<List<Teacher>> findAll() {
            return r2dbcRepository.findAll()
                  .map(mapper::toDomain)
                  .collectList();
      }

}