package com.colegiocursosms.infrastructure.input.rest.dto;

import com.colegiocursosms.domain.enums.DayOfWeek;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class ScheduleCourseRequest {

      @NotBlank(message = "El código no puede estar vacío")
      private String code;

      @NotBlank(message = "El ID de la matrícula no puede estar vacío")
      private String enrollmentId;

      @NotBlank(message = "El ID del curso no puede estar vacío")
      private String courseId;

      @NotBlank(message = "El ID del aula no puede estar vacío")
      private String idClassroom;

      @NotNull(message = "El día no puede ser nulo")
      private DayOfWeek day;

      @NotNull(message = "La hora de inicio no puede ser nula")
      private LocalTime startTime;

      @NotNull(message = "La hora de fin no puede ser nula")
      private LocalTime endTime;

      private String portada;
}