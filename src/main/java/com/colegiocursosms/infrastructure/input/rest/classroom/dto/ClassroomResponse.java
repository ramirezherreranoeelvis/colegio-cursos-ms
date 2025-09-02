package com.colegiocursosms.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la respuesta de la API al solicitar información de un aula.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomResponse {

      private String id;
      private Integer number;
      private Integer floor;

}