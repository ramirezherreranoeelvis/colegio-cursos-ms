package com.colegiocursosms.infrastructure.input.rest;

import com.colegiocursosms.application.port.input.course.IFindCoursesUseCase;
import com.colegiocursosms.application.port.input.course.IRegisterCoursesUseCase;
import com.colegiocursosms.infrastructure.input.rest.dto.CourseResponse;
import com.colegiocursosms.infrastructure.input.rest.dto.RegisterCourseRequest;
import com.colegiocursosms.infrastructure.input.rest.mapper.CourseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@RestController
class CoursesController {

      private final IRegisterCoursesUseCase registerCoursesUseCase;
      private final IFindCoursesUseCase findCoursesUseCase;
      private final CourseMapper courseMapper;

      /**
       * <ol>
       *       <li>El método ahora devuelve un Mono con una lista de CourseResponse</li>
       *       <li>Llama al caso de uso, que devuelve Mono<List<Course>></li>
       *       <li>Usa .map en el Mono para acceder a la lista</li>
       *       <li>Convierte cada Course del stream a CourseResponse</li>
       *       <li>Recolecta los resultados en una nueva lista de CourseResponse</li>
       *       <li>Envuelve la lista final en un ResponseEntity con estado 200 OK</li>
       * <ol/>
       *
       * @return
       */
      @GetMapping("")
      public Mono<ResponseEntity<List<CourseResponse>>> findAllCourses() {
            return findCoursesUseCase.findAll()
                  .map(courseList -> courseList.stream()
                        .map(courseMapper::mapToResponse)
                        .toList()
                  )
                  .map(ResponseEntity::ok);
      }

      /**
       * Maneja la petición POST para registrar un nuevo curso.
       * Recibe un CourseRequest, lo valida, lo convierte a un objeto de dominio,
       * lo pasa al caso de uso para ser procesado y guardado,
       * y finalmente devuelve el curso creado con un estado HTTP 201.
       */
      @PostMapping("/register")
      public Mono<ResponseEntity<CourseResponse>> registerCourse(@Valid @RequestBody RegisterCourseRequest courseRequest) {
            return Mono.just(courseRequest)
                  .map(courseMapper::toDomain)
                  .flatMap(registerCoursesUseCase::registerCourse)
                  .map(courseMapper::mapToResponse)
                  .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
      }

}
