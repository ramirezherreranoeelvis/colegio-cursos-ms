package com.colegiocursosms.domain;

import com.colegiocursosms.domain.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CourseContentItem extends Auditable {

      private String id;
      private String courseScheduleId;
      private String parentId;
      private ContentType itemType;
      private String title;
      private String dataPayload; // JSON con datos específicos
      private Integer displayOrder;
}