package com.colegiocursosms.infrastructure.input.rest.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO para la solicitud de asignar un profesor a un horario de curso.
 */
@Data
public class AssignTeacherRequest {

      @NotBlank(message = "El ID del profesor no puede estar vacío")
      private String teacherId;

}