package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.StudentSubmission;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida que define el contrato para las operaciones de
 * persistencia de la entidad StudentSubmission.
 */
public interface IStudentSubmissionRepository {

      Mono<StudentSubmission> save(StudentSubmission submission);

      Mono<StudentSubmission> findById(String id);

      Mono<StudentSubmission> findByStudentIdAndContentItemId(String studentId, String contentItemId);
}