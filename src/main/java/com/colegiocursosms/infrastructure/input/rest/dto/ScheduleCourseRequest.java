package com.colegiocursosms.infrastructure.input.rest.dto;

import com.colegiocursosms.domain.enums.DayOfWeek;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
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

      @NotNull(message = "El día no puede ser nulo")
      private DayOfWeek day;

      @NotNull(message = "La hora de inicio no puede ser nula")
      private LocalTime startTime;

      @NotNull(message = "La hora de fin no puede ser nula")
      private LocalTime endTime;

      @NotNull(message = "El número de aula no puede ser nulo")
      @Min(value = 1, message = "El número de aula debe ser mayor a cero")
      private Integer classroomNumber;

      @NotNull(message = "El piso del aula no puede ser nulo")
      private Integer classroomFloor;

      private String portada;
}