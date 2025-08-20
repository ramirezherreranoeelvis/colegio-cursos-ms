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

      private final IR2dbcEnrollmentRepository enrollmentRepository;
      private final EntityMapper entityMapper;
      private final DomainMapper domainMapper;

      @Override
      public Mono<Enrollment> save(Enrollment enrollmentDomain) {
            var entity = entityMapper.toEntity(enrollmentDomain);
            entity.markNew(); // Forzamos un INSERT
            return enrollmentRepository.save(entity)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<Enrollment> update(Enrollment enrollmentDomain) {
            var entity = entityMapper.toEntity(enrollmentDomain);
            // No llamamos a markNew(), por lo que Spring hará un UPDATE
            return enrollmentRepository.save(entity)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<Enrollment> findById(String id) {
            return enrollmentRepository.findById(id)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<Boolean> existsById(String id) {
            return enrollmentRepository.existsById(id);
      }

      @Override
      public Mono<List<Enrollment>> findAll() {
            return enrollmentRepository.findAll()
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }

      @Override
      public Mono<List<Enrollment>> findAllByYear(Year year) {
            return enrollmentRepository.findAllByYear(year)
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }

      @Override
      public Mono<List<Enrollment>> findAllByGrade(String grade) {
            return enrollmentRepository.findAllByGrade(grade)
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }

      @Override
      public Mono<List<Enrollment>> findAllByEnrolled(Integer enrolled) {
            return enrollmentRepository.findAllByEnrolled(enrolled)
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }

      @Override
      public Mono<Boolean> existsByGradeAndYear(String grade, Year year) {
            return enrollmentRepository.existsByGradeAndYear(grade, year);
      }

      /**
       * Busca una matrícula por su grado y año.
       */
      @Override
      public Mono<Enrollment> findByGradeAndYear(String grade, Year year) {
            return enrollmentRepository.findByGradeAndYear(grade, year)
                  .flatMap(domainMapper::toDomain);
      }

}