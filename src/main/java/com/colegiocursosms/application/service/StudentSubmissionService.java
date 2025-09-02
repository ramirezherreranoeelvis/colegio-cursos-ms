package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.submission.ISubmitContentUseCase;
import com.colegiocursosms.application.port.output.ICourseContentItemRepository;
import com.colegiocursosms.application.port.output.IStudentRepository;
import com.colegiocursosms.application.port.output.IStudentSubmissionRepository;
import com.colegiocursosms.domain.CourseContentItem;
import com.colegiocursosms.domain.StudentSubmission;
import com.colegiocursosms.domain.enums.ContentType;
import com.colegiocursosms.domain.exception.InvalidContentTypeForSubmissionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Set;

@Log4j2
@Service
@RequiredArgsConstructor
public class StudentSubmissionService implements ISubmitContentUseCase {

      private final IStudentSubmissionRepository submissionRepository;
      private final IStudentRepository studentRepository;
      private final ICourseContentItemRepository contentItemRepository;

      private static final Set<ContentType> SUBMITTABLE_TYPES = Set.of(
            ContentType.ASSIGNMENT,
            ContentType.EXAM,
            ContentType.FORUM
      );

      /**
       * Guarda la entrega de un estudiante después de validar que:
       * 1. El estudiante exista.
       * 2. El item de contenido exista.
       * 3. El item de contenido sea de un tipo "entregable" (ASSIGNMENT, EXAM, FORUM).
       */
      @Override
      public Mono<StudentSubmission> submitContent(StudentSubmission submission) {
            log.info("Iniciando validación para la entrega del estudiante {} al item {}",
                  submission.getStudentId(), submission.getContentItemId());

            // 1. Buscamos el item de contenido para validar su tipo
            Mono<CourseContentItem> contentItemMono = contentItemRepository.findById(submission.getContentItemId())
                  .switchIfEmpty(Mono.error(new RuntimeException("El item de contenido con ID " + submission.getContentItemId() + " no existe.")));

            // 2. Verificamos que el estudiante exista
            Mono<Boolean> studentExistsMono = studentRepository.existsById(submission.getStudentId())
                  .filter(Boolean.TRUE::equals)
                  .switchIfEmpty(Mono.error(new RuntimeException("El estudiante con ID " + submission.getStudentId() + " no existe.")));


            // 3. Encadenamos las validaciones
            return Mono.zip(contentItemMono, studentExistsMono)
                  .flatMap(tuple -> {
                        CourseContentItem contentItem = tuple.getT1();

                        // 4. Validamos que el tipo de contenido sea entregable
                        if (!SUBMITTABLE_TYPES.contains(contentItem.getItemType())) {
                              return Mono.error(new InvalidContentTypeForSubmissionException(
                                    "No se puede hacer una entrega a un item de tipo " + contentItem.getItemType()
                              ));
                        }

                        // 5. Si todo es correcto, asignamos la fecha y guardamos
                        submission.setSubmissionDate(Instant.now());
                        log.info("Validaciones correctas. Guardando entrega...");
                        return submissionRepository.save(submission);
                  });
      }
}