package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.IClassroomRepository;
import com.colegiocursosms.domain.Classroom;
import com.colegiocursosms.domain.mapper.DomainMapper;
import com.colegiocursosms.infrastructure.output.persistence.mapper.EntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

/**
 * Adaptador que implementa el puerto de persistencia de Classroom, utilizando
 * Spring Data R2DBC como tecnología subyacente.
 */
@Repository
@RequiredArgsConstructor
public class R2dbcClassroomRepository implements IClassroomRepository {

      private final IR2dbcClassroomRepository classroomRepository;
      private final EntityMapper entityMapper;
      private final DomainMapper domainMapper;

      @Override
      @Transactional
      public Mono<Classroom> save(Classroom classroomDomain) {
            if (classroomDomain.getId() == null) {
                  classroomDomain.setId(UUID.randomUUID().toString());
            }
            var classroomEntity = entityMapper.toEntity(classroomDomain);
            classroomEntity.markNew();
            return classroomRepository.save(classroomEntity)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<Boolean> existsByNumber(Integer number) {
            return classroomRepository.existsByNumber(number);
      }

      @Override
      public Mono<Classroom> findByNumber(Integer number) {
            return classroomRepository.findByNumber(number)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<List<Classroom>> findAll() {
            return classroomRepository.findAll()
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }
}