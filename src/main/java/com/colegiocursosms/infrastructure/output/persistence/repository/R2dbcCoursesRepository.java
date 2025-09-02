package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.ICourseRepository;
import com.colegiocursosms.domain.Course;
import com.colegiocursosms.domain.mapper.DomainMapper;
import com.colegiocursosms.infrastructure.output.persistence.mapper.EntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcCoursesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Log4j2
@Repository
@RequiredArgsConstructor
public class R2dbcCoursesRepository implements ICourseRepository {

      private final IR2dbcCoursesRepository r2dbcRepository;
      private final EntityMapper entityMapper;
      private final DomainMapper domainMapper;

      @Override
      @Transactional
      public Mono<Course> save(Course courseDomain) {
            if (courseDomain.getId() == null) {
                  courseDomain.setId(UUID.randomUUID().toString());
            }
            var courseEntity = entityMapper.toEntity(courseDomain);
            courseEntity.markNew();
            return r2dbcRepository.save(courseEntity)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      @Transactional
      public Mono<Course> findByName(String name) {
            return r2dbcRepository.findByName(name)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      @Transactional
      public Mono<Boolean> existsByName(String name) {
            return r2dbcRepository.existsByName(name);
      }

      @Override
      public Mono<List<Course>> findAll() {
            return r2dbcRepository.findAll()
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }

      /**
       * Busca un curso por su ID.
       */
      @Override
      @Transactional
      public Mono<Course> findById(String id) {
            return r2dbcRepository.findById(id)
                  .flatMap(domainMapper::toDomain);
      }

}
