package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.IStudentSubmissionRepository;
import com.colegiocursosms.domain.StudentSubmission;
import com.colegiocursosms.infrastructure.output.persistence.mapper.StudentSubmissionEntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcStudentSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class R2dbcStudentSubmissionRepository implements IStudentSubmissionRepository {

      private final IR2dbcStudentSubmissionRepository r2dbcRepository;
      private final StudentSubmissionEntityMapper mapper;

      @Override
      public Mono<StudentSubmission> save(StudentSubmission submission) {
            if (submission.getId() == null) {
                  submission.setId(UUID.randomUUID().toString());
            }
            var entity = mapper.toEntity(submission);
            entity.markNew(); // Forzar INSERT para nuevas entregas
            return r2dbcRepository.save(entity)
                  .map(mapper::toDomain);
      }

      @Override
      public Mono<StudentSubmission> findById(String id) {
            return r2dbcRepository.findById(id)
                  .map(mapper::toDomain);
      }

      @Override
      public Mono<StudentSubmission> findByStudentIdAndContentItemId(String studentId, String contentItemId) {
            return r2dbcRepository.findByStudentIdAndContentItemId(studentId, contentItemId)
                  .map(mapper::toDomain);
      }
}