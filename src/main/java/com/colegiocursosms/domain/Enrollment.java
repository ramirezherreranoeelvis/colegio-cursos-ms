package com.colegiocursosms.domain;

import com.colegiocursosms.domain.enums.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

/**
 * Representa una proyección de los datos de una Matrícula (Enrollment),
 * replicados desde otro microservicio.
 */
@Data
@Builder // Usamos @Builder en lugar de @SuperBuilder al no tener herencia
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

      private String id;
      private Integer enrolled;
      private String grade;
      private Year year;
      private PeriodType periodType;

}