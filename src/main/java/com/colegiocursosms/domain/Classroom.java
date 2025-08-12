package com.colegiocursosms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Representa el objeto de negocio para un Aula (Classroom).
 * Contiene la lógica y los datos principales de un aula,
 * incluyendo su ID, número y piso.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Classroom extends Auditable {

      private String id;
      private Integer number;
      private Integer floor;

}