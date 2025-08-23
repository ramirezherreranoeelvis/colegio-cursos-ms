package com.colegiocursosms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

      private String id;
      private String name;
      private String surnamePaternal;
      private String surnameMaternal;
      private String fatherId;
      private String motherId;
      private String representativeId;
}