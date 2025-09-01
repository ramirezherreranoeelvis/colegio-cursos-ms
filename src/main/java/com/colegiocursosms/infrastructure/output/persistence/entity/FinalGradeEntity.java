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

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table("final_grades")
public class FinalGradeEntity extends AuditableEntity implements Persistable<String> {

      @Id
      private String id;

      @Column("student_id")
      private String studentId;

      @Column("course_schedule_id")
      private String courseScheduleId;

      @Column("period_name")
      private String periodName;

      @Column("final_score")
      private BigDecimal finalScore;

      @Transient
      private boolean isNew;

      @Override
      public boolean isNew() { return this.isNew; }

      public void markNew() { this.isNew = true; }
}