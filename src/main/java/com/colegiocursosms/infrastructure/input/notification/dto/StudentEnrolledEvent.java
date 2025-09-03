package com.colegiocursosms.infrastructure.input.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa el evento de un estudiante siendo matriculado,
 * recibido desde Kafka (producido por enrollment-ms).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnrolledEvent {

      private String studentId;
      private String enrollmentId;
}