package com.colegiocursosms.infrastructure.input.rest.dto;

import com.colegiocursosms.domain.enums.DayOfWeek;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

/**
 * DTO para la respuesta de la API con la información de un horario de curso.
 */
@Data
@Builder
public class CourseScheduleResponse {
      private String id;
      private String code;
      private String enrollmentId;
      private String courseId;
      private DayOfWeek day;
      private LocalTime startTime;
      private LocalTime endTime;
      private Integer classroomNumber;
      private Integer classroomFloor;
      private String portada;
}