package com.colegiocursosms.infrastructure.input.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreatedEvent {

      private String id;
      private String dni;
      private String name;
      private String surnamePaternal;
      private String surnameMaternal;
      private String fatherId;
      private String motherId;
      private String representativeId;

}
