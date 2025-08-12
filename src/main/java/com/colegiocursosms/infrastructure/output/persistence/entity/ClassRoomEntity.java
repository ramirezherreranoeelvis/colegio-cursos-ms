package com.colegiocursosms.infrastructure.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table("classrooms")
public class ClassRoomEntity extends AuditableEntity implements Persistable<String> {

      @Id
      private String id;

      @Column("number")
      private Integer number;

      @Column("floor")
      private Integer floor;

      @Transient
      private boolean isNew;

      @Override
      public boolean isNew() {
            return isNew || id == null;
      }

      public void markNew() {
            this.isNew = true;
      }

      public static ClassRoomEntity newUser() {
            return ClassRoomEntity.builder()
                  .id(UUID.randomUUID().toString())
                  .isNew(true)
                  .build();
      }
}
