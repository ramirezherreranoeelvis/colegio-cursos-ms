package com.colegiocursosms.infrastructure.input.rest;

import com.colegiocursosms.application.port.input.courseschedule.IAssignTeacherToScheduleUseCase;
import com.colegiocursosms.application.port.input.courseschedule.IFindCourseSchedulesUseCase;
import com.colegiocursosms.application.port.input.courseschedule.IScheduleCourseUseCase;
import com.colegiocursosms.infrastructure.input.rest.dto.AssignTeacherRequest;
import com.colegiocursosms.infrastructure.input.rest.dto.AssignmentResponse;
import com.colegiocursosms.infrastructure.input.rest.dto.CourseScheduleResponse;
import com.colegiocursosms.infrastructure.input.rest.dto.ScheduleCourseRequest;
import com.colegiocursosms.infrastructure.input.rest.mapper.AssignmentMapper;
import com.colegiocursosms.infrastructure.input.rest.mapper.CourseScheduleMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones sobre los Horarios de Cursos.
 */
@Log4j2
@RestController
@RequestMapping("/api/course-schedules")
@RequiredArgsConstructor
public class CourseSchedulesController {

      private final IFindCourseSchedulesUseCase findUseCase;
      private final CourseScheduleMapper mapper;
      private final IAssignTeacherToScheduleUseCase assignTeacherUseCase;
      private final AssignmentMapper assignmentMapper;
      private final IScheduleCourseUseCase scheduleCourseUseCase;

      /**
       * Maneja la petición POST para programar un nuevo horario de curso.
       */
      @PostMapping("/register")
      public Mono<ResponseEntity<CourseScheduleResponse>> scheduleCourse(@Valid @RequestBody ScheduleCourseRequest request) {
            return Mono.just(request)
                  .map(mapper::toDomain)
                  .flatMap(scheduleCourseUseCase::registerCourse)
                  .map(mapper::toResponse)
                  .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
      }

      /**
       * Maneja la petición GET para obtener todos los horarios de cursos.
       */
      @GetMapping("")
      public Mono<ResponseEntity<List<CourseScheduleResponse>>> findAll() {
            return findUseCase.findAll()
                  .map(scheduleList -> scheduleList.stream()
                        .map(mapper::toResponse)
                        .toList()
                  )
                  .map(ResponseEntity::ok);
      }

      /**
       * Maneja la petición GET para buscar un horario por su código único.
       */
      @GetMapping("/code/{code}")
      public Mono<ResponseEntity<CourseScheduleResponse>> findByCode(@PathVariable String code) {
            return findUseCase.findByCode(code)
                  .map(mapper::toResponse)
                  .map(ResponseEntity::ok);
      }


      /**
       * Maneja la petición POST para asignar un profesor a un horario de curso existente.
       */
      @PostMapping("/{scheduleId}/teachers")
      public Mono<ResponseEntity<AssignmentResponse>> assignTeacherToSchedule(
            @PathVariable String scheduleId,
            @Valid @RequestBody AssignTeacherRequest request) {

            return assignTeacherUseCase.assignTeacher(scheduleId, request.getTeacherId())
                  .map(assignmentMapper::toResponse)
                  .map(responseDto -> ResponseEntity.status(HttpStatus.CREATED).body(responseDto));
      }

}