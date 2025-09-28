package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.ICourseScheduleStudentRepository;
import com.colegiocursosms.domain.CourseScheduleStudent;
import com.colegiocursosms.infrastructure.output.persistence.mapper.CourseScheduleStudentEntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcCourseScheduleStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class R2dbcCourseScheduleStudentRepository implements ICourseScheduleStudentRepository {

      private final IR2dbcCourseScheduleStudentRepository r2dbcRepository;
      private final CourseScheduleStudentEntityMapper mapper;

      @Override
      public Mono<CourseScheduleStudent> save(CourseScheduleStudent association) {
            if (association.getId() == null) {
                  association.setId(UUID.randomUUID().toString());
            }
            var entity = mapper.toEntity(association);
            entity.markNew();
            return r2dbcRepository.save(entity)
                  .map(mapper::toDomain);
      }
}