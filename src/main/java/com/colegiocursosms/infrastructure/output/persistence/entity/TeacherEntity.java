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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("teacher")
public class TeacherEntity implements Persistable<String> {

      @Id
      private String id;

      @Column("name")
      private String name;

      @Column("surname_paternal")
      private String surnamePaternal;

      @Column("surname_maternal")
      private String surnameMaternal;

      @Column("phone")
      private String phone;

      @Transient
      private boolean isNew;

      @Override
      public boolean isNew() {
            // El ID siempre vendrá del evento de Kafka, así que nos basamos en el flag isNew
            return this.isNew;
      }

      public void markNew() {
            this.isNew = true;
      }
}