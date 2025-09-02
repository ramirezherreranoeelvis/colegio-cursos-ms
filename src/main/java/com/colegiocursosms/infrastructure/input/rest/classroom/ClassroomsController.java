package com.colegiocursosms.infrastructure.input.rest.classroom;

import com.colegiocursosms.application.port.input.classroom.IFindClassroomsUseCase;
import com.colegiocursosms.application.port.input.classroom.IRegisterClassroomUseCase;
import com.colegiocursosms.infrastructure.input.rest.classroom.dto.ClassroomResponse;
import com.colegiocursosms.infrastructure.input.rest.classroom.dto.RegisterClassroomRequest;
import com.colegiocursosms.infrastructure.input.rest.classroom.mapper.ClassroomMapper; // <-- Import cambiado
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * Controlador REST para gestionar las operaciones sobre las Aulas.
 */
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/classrooms")
@RestController
public class ClassroomsController {

      private final IRegisterClassroomUseCase registerClassroomUseCase;
      private final IFindClassroomsUseCase findClassroomsUseCase;
      private final ClassroomMapper mapper; // <-- Dependencia cambiada

      /**
       * Maneja la petición GET para obtener todas las aulas.
       */
      @GetMapping("")
      public Mono<ResponseEntity<List<ClassroomResponse>>> findAllClassrooms() {
            return findClassroomsUseCase.findAll()
                  .map(classroomList -> classroomList.stream()
                        .map(mapper::mapToResponse)
                        .toList()
                  )
                  .map(ResponseEntity::ok);
      }

      /**
       * Maneja la petición POST para registrar una nueva aula.
       */
      @PostMapping("/register")
      public Mono<ResponseEntity<ClassroomResponse>> registerClassroom(@Valid @RequestBody RegisterClassroomRequest request) {
            return Mono.just(request)
                  .map(mapper::toDomain)
                  .flatMap(registerClassroomUseCase::registerClassroom)
                  .map(mapper::mapToResponse)
                  .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
      }
}