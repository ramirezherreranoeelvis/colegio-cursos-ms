package com.colegiocursosms.infrastructure.input.rest.grade.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO para la solicitud de registrar una nota final.
 */
@Data
@Builder
public class RecordFinalGradeRequest {

      @NotBlank(message = "El nombre del período (ej. 'Bimestre 1') no puede estar vacío")
      private String periodName;

      @NotNull(message = "La nota final no puede ser nula")
      @DecimalMin(value = "0.0", message = "La nota no puede ser menor que 0")
      @DecimalMax(value = "20.0", message = "La nota no puede ser mayor que 20")
      private BigDecimal finalScore;
}