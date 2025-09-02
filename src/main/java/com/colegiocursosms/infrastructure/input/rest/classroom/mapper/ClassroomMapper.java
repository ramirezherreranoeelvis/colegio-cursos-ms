package com.colegiocursosms.infrastructure.input.rest.classroom.mapper;

import com.colegiocursosms.domain.Classroom;
import com.colegiocursosms.infrastructure.input.rest.classroom.dto.ClassroomResponse;
import com.colegiocursosms.infrastructure.input.rest.classroom.dto.RegisterClassroomRequest;
import org.springframework.stereotype.Component;

/**
 * Mapper responsable de las conversiones entre DTOs y objetos
 * de dominio para la entidad Classroom.
 */
@Component
public class ClassroomMapper {

      /**
       * Convierte un objeto de dominio Classroom a un DTO de respuesta.
       */
      public ClassroomResponse mapToResponse(Classroom classroom) {
            return ClassroomResponse.builder()
                  .id(classroom.getId())
                  .number(classroom.getNumber())
                  .floor(classroom.getFloor())
                  .build();
      }

      /**
       * Convierte un DTO de solicitud RegisterClassroomRequest a un objeto de dominio Classroom.
       */
      public Classroom toDomain(RegisterClassroomRequest request) {
            return Classroom.builder()
                  .number(request.getNumber())
                  .floor(request.getFloor())
                  .build();
      }
}