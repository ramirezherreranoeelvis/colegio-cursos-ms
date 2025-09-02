package com.colegiocursosms.infrastructure.input.rest.dto;

import com.colegiocursosms.domain.enums.ContentType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ContentItemResponse {

      private String id;
      private String courseScheduleId;
      private String parentId;
      private ContentType itemType;
      private String title;
      private String dataPayload;
      private Integer displayOrder;
      private String createdBy;
      private Instant createdDate;
      @Builder.Default
      private List<ContentItemResponse> children = new ArrayList<>();

}