package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.IFindEnrollmentsUseCase;
import com.colegiocursosms.application.port.input.IProcessEnrollmentUseCase;
import com.colegiocursosms.application.port.output.IEnrollmentRepository;
import com.colegiocursosms.domain.Enrollment;
import com.colegiocursosms.domain.exception.EnrollmentAlreadyExistsException;
import com.colegiocursosms.domain.exception.EnrollmentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Year;
import java.util.List;

/**
 * Implementación de los casos de uso para la gestión de la réplica
 * de datos de Matrículas.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class EnrollmentService implements IProcessEnrollmentUseCase, IFindEnrollmentsUseCase {

      private final IEnrollmentRepository enrollmentRepository;

      /**
       * Guarda (inserta) una nueva matrícula, solo si no existe una para el mismo grado y año.
       */
      @Override
      public Mono<Enrollment> saveEnrollment(Enrollment enrollment) {
            //... lógica de validación ...
            return enrollmentRepository.existsByGradeAndYear(enrollment.getGrade(), enrollment.getYear()).filter(Boolean.FALSE::equals).switchIfEmpty(Mono.error(new EnrollmentAlreadyExistsException("Ya existe una matrícula para el grado " + enrollment.getGrade() + " y el año " + enrollment.getYear()))).then(Mono.defer(() -> {
                  log.info("Insertando nueva matrícula para el grado {}", enrollment.getGrade());
                  // Llama al método 'save' que ahora es solo para insertar
                  return enrollmentRepository.save(enrollment);
            }));
      }

      /**
       * Actualiza una matrícula existente, solo si el ID de la matrícula ya existe.
       */
      @Override
      public Mono<Enrollment> updateEnrollment(Enrollment enrollment) {
            log.info("Validando si la matrícula con ID {} existe antes de actualizar", enrollment.getId());
            return enrollmentRepository.findById(enrollment.getId()).switchIfEmpty(Mono.error(new EnrollmentNotFoundException("No se encontró la matrícula con ID " + enrollment.getId() + " para actualizar."))).flatMap(existingEnrollment -> {
                  log.info("Actualizando matrícula con ID {}", enrollment.getId());
                  return enrollmentRepository.update(enrollment);
            });
      }

      @Override
      public Mono<Enrollment> findById(String id) {
            return enrollmentRepository.findById(id);
      }

      @Override
      public Mono<Boolean> existsById(String id) {
            return enrollmentRepository.existsById(id);
      }

      @Override
      public Mono<List<Enrollment>> findAll() {
            return enrollmentRepository.findAll();
      }

      @Override
      public Mono<List<Enrollment>> findAllByYear(Year year) {
            return enrollmentRepository.findAllByYear(year);
      }

      @Override
      public Mono<List<Enrollment>> findAllByGrade(String grade) {
            return enrollmentRepository.findAllByGrade(grade);
      }

      @Override
      public Mono<List<Enrollment>> findAllByEnrolled(Integer enrolled) {
            return enrollmentRepository.findAllByEnrolled(enrolled);
      }

      @Override
      public Mono<Boolean> existsByGradeAndYear(String grade, Year year) {
            return enrollmentRepository.existsByGradeAndYear(grade, year);
      }

      @Override
      public Mono<Enrollment> findByGradeAndYear(String grade, Year year) {
            return enrollmentRepository.findByGradeAndYear(grade, year);
      }

}