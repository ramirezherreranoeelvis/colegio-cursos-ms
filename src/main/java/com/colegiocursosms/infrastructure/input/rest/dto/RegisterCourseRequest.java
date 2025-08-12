package com.colegiocursosms.infrastructure.input.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCourseRequest {

      @NotBlank(message = "El nombre del curso no puede estar vacío")
      private String name;

}
