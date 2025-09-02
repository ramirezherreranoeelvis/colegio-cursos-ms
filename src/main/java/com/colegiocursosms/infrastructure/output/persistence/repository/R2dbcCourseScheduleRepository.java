package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.ICourseScheduleRepository;
import com.colegiocursosms.domain.CourseSchedule;
import com.colegiocursosms.domain.mapper.DomainMapper;
import com.colegiocursosms.infrastructure.output.persistence.mapper.EntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcCourseScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class R2dbcCourseScheduleRepository implements ICourseScheduleRepository {

      private final IR2dbcCourseScheduleRepository r2dbcRepository;
      private final EntityMapper entityMapper;
      private final DomainMapper domainMapper;

      @Override
      @Transactional
      public Mono<CourseSchedule> save(CourseSchedule scheduleDomain) {
            if (scheduleDomain.getId() == null) {
                  scheduleDomain.setId(UUID.randomUUID().toString());
            }
            var scheduleEntity = entityMapper.toEntity(scheduleDomain);
            scheduleEntity.markNew(); // Forzar un INSERT
            return r2dbcRepository.save(scheduleEntity)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<CourseSchedule> findById(String id) {
            return r2dbcRepository.findById(id)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<Boolean> existsById(String id) {
            return r2dbcRepository.existsById(id);
      }

      @Override
      public Mono<CourseSchedule> findByCode(String code) {
            return r2dbcRepository.findByCode(code)
                  .flatMap(domainMapper::toDomain);
      }

      @Override
      public Mono<Boolean> existsByCode(String code) {
            return r2dbcRepository.existsByCode(code);
      }

      @Override
      public Mono<List<CourseSchedule>> findAll() {
            return r2dbcRepository.findAll()
                  .flatMap(domainMapper::toDomain)
                  .collectList();
      }

}