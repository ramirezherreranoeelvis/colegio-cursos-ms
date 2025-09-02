package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.IEnrollmentRepository;
import com.colegiocursosms.domain.Enrollment;
import com.colegiocursosms.domain.mapper.DomainMapper;
import com.colegiocursosms.infrastructure.output.persistence.mapper.EntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcEnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Year;
import java.util.List;

/**
 * Adaptador que implementa el puerto de persistencia de Enrollment, utilizando
 * Spring Data R2DBC. Se encarga de la lógica de "upsert".
 */
@Repository
@RequiredArgsConstructor
public class R2dbcEnrollmentRepository implements IEnrollmentRepository {

      private final IR2dbcEnrollmentRepository r2dbcRepository;
      private final EntityMapper entityMapper;
      private final DomainMapper domainMapper;

      @Override
      public Mono<Enrollment> save(Enrollment enrollmentDomain) {
            var entity = entityMapper.toEntity(enrollmentDomain);
            entity.markNew(); // Forzamos un INSERT
            return r2dbcRepository.save(entity)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<Enrollment> update(Enrollment enrollmentDomain) {
            var entity = entityMapper.toEntity(enrollmentDomain);
            // No llamamos a markNew(), por lo que Spring hará un UPDATE
            return r2dbcRepository.save(entity)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<Enrollment> findById(String id) {
            return r2dbcRepository.findById(id)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<Boolean> existsById(String id) {
            return r2dbcRepository.existsById(id);
      }

      @Override
      public Mono<List<Enrollment>> findAll() {
            return r2dbcRepository.findAll()
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }

      @Override
      public Mono<List<Enrollment>> findAllByYear(Year year) {
            return r2dbcRepository.findAllByYear(year)
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }

      @Override
      public Mono<List<Enrollment>> findAllByGrade(String grade) {
            return r2dbcRepository.findAllByGrade(grade)
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }

      @Override
      public Mono<List<Enrollment>> findAllByEnrolled(Integer enrolled) {
            return r2dbcRepository.findAllByEnrolled(enrolled)
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }

      @Override
      public Mono<Boolean> existsByGradeAndYear(String grade, Year year) {
            return r2dbcRepository.existsByGradeAndYear(grade, year);
      }

      /**
       * Busca una matrícula por su grado y año.
       */
      @Override
      public Mono<Enrollment> findByGradeAndYear(String grade, Year year) {
            return r2dbcRepository.findByGradeAndYear(grade, year)
                  .flatMap(domainMapper::toDomain);
      }

}