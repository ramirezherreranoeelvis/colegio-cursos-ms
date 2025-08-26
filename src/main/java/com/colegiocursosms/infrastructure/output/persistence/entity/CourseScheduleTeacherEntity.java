package com.colegiocursosms.infrastructure.output.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Table("course_schedule_teachers")
public class CourseScheduleTeacherEntity extends AuditableEntity implements Persistable<String> {

      @Id
      private String id;
      @Column("course_schedule_id")
      private String courseScheduleId;
      @Column("teacher_id")
      private String teacherId;
      @Column("start_date")
      private LocalDate startDate;
      @Column("end_date")
      private LocalDate endDate;
      @Column("role")
      private String role;

      @Transient
      private boolean isNew;

      @Override
      public boolean isNew() {return this.isNew;}

      public void markNew() {this.isNew = true;}

}