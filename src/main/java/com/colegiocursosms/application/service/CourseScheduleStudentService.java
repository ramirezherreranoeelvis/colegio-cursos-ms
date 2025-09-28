package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.enrollment.IAssociateStudentToSchedulesUseCase;
import com.colegiocursosms.application.port.output.ICourseScheduleRepository;
import com.colegiocursosms.application.port.output.ICourseScheduleStudentRepository;
import com.colegiocursosms.domain.CourseScheduleStudent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@RequiredArgsConstructor
public class CourseScheduleStudentService implements IAssociateStudentToSchedulesUseCase {

      private final ICourseScheduleRepository scheduleRepository;
      private final ICourseScheduleStudentRepository associationRepository;

      /**
       * Implementa la lógica de negocio para asociar un estudiante a todos los
       * horarios de curso de su matrícula.
       * 1. Busca todos los horarios por enrollmentId.
       * 2. Para cada horario encontrado, crea y guarda una nueva entrada en la
       * tabla de unión course_schedule_students.
       */
      @Override
      public Mono<Void> associateStudentToSchedules(String studentId, String enrollmentId) {
            log.info("Iniciando asociación para estudiante {} y matrícula {}", studentId, enrollmentId);

            return scheduleRepository.findAllByEnrollmentId(enrollmentId)
                  .flatMapMany(Flux::fromIterable)
                  .flatMap(schedule -> {
                        log.info("Asociando estudiante {} al horario {}", studentId, schedule.getId());
                        CourseScheduleStudent association = CourseScheduleStudent.builder()
                              .studentId(studentId)
                              .courseScheduleId(schedule.getId())
                              .build();
                        return associationRepository.save(association);
                  })
                  .then();
      }
}