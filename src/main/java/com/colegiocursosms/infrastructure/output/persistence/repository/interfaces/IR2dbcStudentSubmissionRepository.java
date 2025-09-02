package com.colegiocursosms.infrastructure.output.persistence.repository.interfaces;

import com.colegiocursosms.infrastructure.output.persistence.entity.StudentSubmissionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IR2dbcStudentSubmissionRepository extends ReactiveCrudRepository<StudentSubmissionEntity, String> {

      /**
       * Busca una entrega específica por el ID del estudiante y el ID del item de contenido (la tarea).
       */
      Mono<StudentSubmissionEntity> findByStudentIdAndContentItemId(String studentId, String contentItemId);

}