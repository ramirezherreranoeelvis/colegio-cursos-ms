package com.colegiocursosms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa el objeto de negocio para un Profesor (Teacher).
 * Es una réplica de los datos provenientes de auth-ms.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

      private String id;
      private String name;
      private String surnamePaternal;
      private String surnameMaternal;
      private String phone;

}