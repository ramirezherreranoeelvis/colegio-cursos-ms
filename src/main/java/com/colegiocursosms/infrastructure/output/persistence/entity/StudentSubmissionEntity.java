package com.colegiocursosms.infrastructure.output.persistence.entity;

import com.colegiocursosms.domain.enums.SubmissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("student_submissions")
public class StudentSubmissionEntity implements Persistable<String> {

      @Id
      private String id;

      @Column("student_id")
      private String studentId;

      @Column("content_item_id")
      private String contentItemId;

      @Column("submission_type")
      private SubmissionType submissionType;

      @Column("submission_content")
      private String submissionContent;

      @Column("submission_date")
      private Instant submissionDate;

      @Transient
      private boolean isNew;

      @Override
      public boolean isNew() {return this.isNew;}

      public void markNew() {this.isNew = true;}

}