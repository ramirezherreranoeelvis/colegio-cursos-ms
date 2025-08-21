package com.colegiocursosms.infrastructure.input.notification.dto;

import com.colegiocursosms.domain.enums.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Year;

/**
 * DTO que representa el evento de una matrícula creada, recibido desde Kafka.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentCreatedEvent {
      private String id;
      private Integer enrolled;
      private String grade;
      private Year year;
      private PeriodType periodType;
}