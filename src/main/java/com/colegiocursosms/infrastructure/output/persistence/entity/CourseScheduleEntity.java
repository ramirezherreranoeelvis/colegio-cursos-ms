package com.colegiocursosms.infrastructure.output.persistence.entity;

import com.colegiocursosms.domain.enums.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("course_schedules")
public class CourseScheduleEntity implements Persistable<String> {

      @Id
      private String id;

      @Column("code")
      private String code;

      @Column("id_enrollment")
      private String enrollmentId;

      @Column("id_course")
      private String courseId;

      @Column("day")
      private DayOfWeek day;

      @Column("start_time")
      private LocalTime startTime;

      @Column("end_time")
      private LocalTime endTime;

      @Column("classroom_number")
      private Integer classroomNumber;

      @Column("classroom_floor")
      private Integer classroomFloor;

      @Column("portada")
      private String portada;

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