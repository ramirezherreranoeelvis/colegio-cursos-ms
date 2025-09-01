package com.colegiocursosms.infrastructure.output.persistence.entity;

import com.colegiocursosms.domain.enums.ContentType;
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

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table("course_content_items")
public class CourseContentItemEntity extends AuditableEntity implements Persistable<String> {

      @Id
      private String id;

      @Column("course_schedule_id")
      private String courseScheduleId;

      @Column("parent_id")
      private String parentId;

      @Column("item_type")
      private ContentType itemType;

      @Column("title")
      private String title;

      @Column("data_payload")
      private String dataPayload; // Guardamos el JSON como String

      @Column("display_order")
      private Integer displayOrder;

      @Transient
      private boolean isNew;

      @Override
      public boolean isNew() { return this.isNew; }

      public void markNew() { this.isNew = true; }
}