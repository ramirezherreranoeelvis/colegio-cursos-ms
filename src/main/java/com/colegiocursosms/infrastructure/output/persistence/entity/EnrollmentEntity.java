package com.colegiocursosms.infrastructure.output.persistence.entity;

import com.colegiocursosms.domain.enums.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Year;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("enrollments")
public class EnrollmentEntity implements Persistable<String> {

      @Id
      private String id;

      @Column("enrolled")
      private Integer enrolled;

      @Column("grade")
      private String grade;

      @Column("year")
      private Year year;

      @Column("period_type")
      private PeriodType periodType;

      @Transient
      private boolean isNew;

      @Override
      public boolean isNew() {
            return isNew || id == null;
      }

      public void markNew() {
            this.isNew = true;
      }

}