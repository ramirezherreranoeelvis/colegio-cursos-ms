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
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table("student_grades")
public class StudentGradeEntity extends AuditableEntity implements Persistable<String> {

      @Id
      private String id;

      @Column("student_id")
      private String studentId;

      @Column("content_item_id")
      private String contentItemId;

      @Column("score")
      private BigDecimal score;

      @Column("submission_date")
      private Instant submissionDate;

      @Column("comments")
      private String comments;

      @Transient
      private boolean isNew;

      @Override
      public boolean isNew() { return this.isNew; }

      public void markNew() { this.isNew = true; }
}