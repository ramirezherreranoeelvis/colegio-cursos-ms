package com.colegiocursosms.infrastructure.output.persistence.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id; // <-- Importante
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("course_schedule_students")
public class CourseScheduleStudentEntity implements Persistable<String> {

      @Id
      private String id;

      @Column("course_schedule_id")
      private String courseScheduleId;

      @Column("student_id")
      private String studentId;

      @Transient
      private boolean isNew;

      @Override
      public boolean isNew() {
            return this.isNew || this.id == null;
      }

      public void markNew() {
            this.isNew = true;
      }
}