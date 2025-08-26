package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.CourseScheduleTeacher;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Puerto de salida que define el contrato para las operaciones de
 * persistencia de la entidad de asignación CourseScheduleTeacher.
 */
public interface ICourseScheduleTeacherRepository {

      Mono<CourseScheduleTeacher> save(CourseScheduleTeacher assignment);

      Mono<List<CourseScheduleTeacher>> findAllByCourseScheduleId(String courseScheduleId);

      Mono<Boolean> existsByCourseScheduleIdAndTeacherId(String scheduleId, String teacherId);
}