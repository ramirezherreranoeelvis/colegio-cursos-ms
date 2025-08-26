package com.colegiocursosms.infrastructure.input.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO para la respuesta de la API al crear una nueva asignación de profesor a horario.
 */
@Data
@Builder
public class AssignmentResponse {

      private String courseScheduleId;
      private String teacherId;
      private LocalDate startDate;
      private String role;

}