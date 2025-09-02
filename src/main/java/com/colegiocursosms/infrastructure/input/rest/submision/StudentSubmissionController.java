package com.colegiocursosms.infrastructure.input.rest.submision;

import com.colegiocursosms.application.port.input.submission.ISubmitContentUseCase;
import com.colegiocursosms.domain.StudentSubmission;
import com.colegiocursosms.infrastructure.input.rest.submision.dto.CreateSubmissionRequest;
import com.colegiocursosms.infrastructure.input.rest.submision.dto.SubmissionResponse;
import com.colegiocursosms.infrastructure.input.rest.submision.mapper.SubmissionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/contents/{contentItemId}/submissions")
@RequiredArgsConstructor
public class StudentSubmissionController {

      private final ISubmitContentUseCase submitContentUseCase;
      private final SubmissionMapper mapper;

      /**
       * Endpoint para que un estudiante realice una entrega a un item de contenido.
       */
      @PostMapping("")
      public Mono<ResponseEntity<SubmissionResponse>> createSubmission(
            @PathVariable String contentItemId,
            @Valid @RequestBody CreateSubmissionRequest request
            // Authentication authentication
      ) {

            // String studentId = authentication.getName();
            String studentId = "1ef32087-b6a0-4dad-a3ec-be0efa59a831";

            StudentSubmission submission = mapper.toDomain(request);
            submission.setContentItemId(contentItemId);
            submission.setStudentId(studentId);

            return submitContentUseCase.submitContent(submission)
                  .map(mapper::toResponse)
                  .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
      }
}