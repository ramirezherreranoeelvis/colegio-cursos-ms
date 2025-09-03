package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.IFinalGradeRepository;
import com.colegiocursosms.domain.FinalGrade;
import com.colegiocursosms.infrastructure.output.persistence.mapper.FinalGradeEntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcFinalGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class R2dbcFinalGradeRepository implements IFinalGradeRepository {

      private final IR2dbcFinalGradeRepository r2dbcRepository;
      private final FinalGradeEntityMapper mapper;

      @Override
      public Mono<FinalGrade> save(FinalGrade finalGrade) {
            if (finalGrade.getId() == null) {
                  finalGrade.setId(UUID.randomUUID().toString());
            }
            var entity = mapper.toEntity(finalGrade);
            entity.markNew(); // Forzar un INSERT
            return r2dbcRepository.save(entity)
                  .map(mapper::toDomain);
      }

      @Override
      public Mono<Boolean> existsByStudentIdAndCourseScheduleIdAndPeriodName(String studentId, String scheduleId, String periodName) {
            return r2dbcRepository.existsByStudentIdAndCourseScheduleIdAndPeriodName(studentId, scheduleId, periodName);
      }
}