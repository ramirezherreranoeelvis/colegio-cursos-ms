package com.colegiocursosms.application.port.input.courseschedule;

import com.colegiocursosms.domain.CourseScheduleTeacher;
import reactor.core.publisher.Mono;

/**
 * Puerto de entrada para el caso de uso de asignar un profesor a un horario de curso.
 */
public interface IAssignTeacherToScheduleUseCase {

      /**
       * Asigna un profesor a un horario de curso específico.
       * @param scheduleId El ID del horario al que se asignará el profesor.
       * @param teacherId El ID del profesor a asignar.
       * @return Un Mono<Void> que se completa cuando la asignación es exitosa.
       */
      Mono<CourseScheduleTeacher> assignTeacher(String scheduleId, String teacherId);

}