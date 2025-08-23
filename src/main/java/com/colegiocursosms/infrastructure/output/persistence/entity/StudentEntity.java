package com.colegiocursosms.infrastructure.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("students")
public class StudentEntity implements Persistable<String> {

      @Id
      private String id;

      @Column("name")
      private String name;

      @Column("surname_paternal")
      private String surnamePaternal;

      @Column("surname_maternal")
      private String surnameMaternal;

      @Column("father_id")
      private String fatherId;

      @Column("mother_id")
      private String motherId;

      @Column("representative_id")
      private String representativeId;

      @Transient
      private boolean isNew;

      @Override
      public boolean isNew() {
            return isNew || id == null;
      }

      public void markNew() {
            this.isNew = true;
      }

      public static StudentEntity newStudent() {
            return StudentEntity.builder()
                  .id(UUID.randomUUID().toString())
                  .isNew(true)
                  .build();
      }
}