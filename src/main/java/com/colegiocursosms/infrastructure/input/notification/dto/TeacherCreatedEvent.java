package com.colegiocursosms.infrastructure.input.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa el evento de un profesor creado o actualizado,
 * recibido desde Kafka (producido por auth-ms).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCreatedEvent {

      private String id;
      private String name;
      private String surnamePaternal;
      private String surnameMaternal;
      private String phone;
}