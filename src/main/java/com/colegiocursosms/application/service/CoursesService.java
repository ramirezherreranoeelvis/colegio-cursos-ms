package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.course.IFindCoursesUseCase;
import com.colegiocursosms.application.port.input.course.IRegisterCoursesUseCase;
import com.colegiocursosms.application.port.output.ICourseRepository;
import com.colegiocursosms.domain.enums.AuditActionType;
import com.colegiocursosms.domain.Course;
import com.colegiocursosms.domain.exception.CourseNameAlreadyExistsException;
import com.colegiocursosms.infrastructure.config.AuditingConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class CoursesService implements IRegisterCoursesUseCase, IFindCoursesUseCase {

      private final ICourseRepository courseRepository;

      /**
       * Registra un nuevo curso con contexto de auditoría.
       * 1. Valida que el nombre del curso no esté en uso.
       * 2. Si no existe, genera un nuevo ID para el curso.
       * 3. Establece el contexto de auditoría (quién realiza la acción).
       * 4. Guarda el curso en la base de datos.
       * 5. Limpia el contexto de auditoría al finalizar, sin importar el resultado.
       */
      @Override
      public Mono<Course> registerCourse(Course course) {
            return courseRepository.existsByName(course.getName())
                  .filter(exists -> !exists)
                  .switchIfEmpty(Mono.error(new CourseNameAlreadyExistsException(
                        "El nombre de curso '" + course.getName() + "' ya está en uso."
                  )))
                  .then(Mono.defer(() -> {
                        // Establecemos el auditor para la transacción
                        AuditingConfig.setAuditor(AuditActionType.SELF_REGISTRATION.getValue());
                        return courseRepository.save(course)
                              .doFinally(signalType -> AuditingConfig.clearAuditor());
                  }));
      }

      @Override
      public Mono<List<Course>> findAll() {
            return this.courseRepository.findAll();
      }

      /**
       * Implementación del caso de uso para buscar un curso por su nombre.
       * Delega la llamada directamente al repositorio.
       */
      @Override
      public Mono<Course> findByName(String name) {
            log.info("Buscando curso por nombre: {}", name);
            return courseRepository.findByName(name);
      }

      @Override
      public Mono<Course> findById(String id) {
            log.info("Buscando curso por ID: {}", id);
            return courseRepository.findById(id);
      }

}
