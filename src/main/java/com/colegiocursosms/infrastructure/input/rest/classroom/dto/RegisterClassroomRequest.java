package com.colegiocursosms.infrastructure.input.rest.classroom.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la solicitud de creación de una nueva aula.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterClassroomRequest {

      @NotNull(message = "El número del aula no puede ser nulo")
      @Min(value = 1, message = "El número del aula debe ser mayor que cero")
      private Integer number;

      @NotNull(message = "El piso no puede ser nulo")
      private Integer floor;

}