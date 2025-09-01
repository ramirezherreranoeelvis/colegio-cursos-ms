package com.colegiocursosms.infrastructure.input.rest.mapper;

import com.colegiocursosms.domain.CourseContentItem;
import com.colegiocursosms.infrastructure.input.rest.dto.ContentItemResponse;
import com.colegiocursosms.infrastructure.input.rest.dto.CreateContentItemRequest;
import org.springframework.stereotype.Component;

@Component
public class ContentItemMapper {

      public CourseContentItem toDomain(CreateContentItemRequest request) {
            return CourseContentItem.builder()
                  .parentId(request.getParentId())
                  .itemType(request.getItemType())
                  .title(request.getTitle())
                  .dataPayload(request.getDataPayload())
                  .displayOrder(request.getDisplayOrder())
                  .build();
      }

      public ContentItemResponse toResponse(CourseContentItem domain) {
            return ContentItemResponse.builder()
                  .id(domain.getId())
                  .courseScheduleId(domain.getCourseScheduleId())
                  .parentId(domain.getParentId())
                  .itemType(domain.getItemType())
                  .title(domain.getTitle())
                  .dataPayload(domain.getDataPayload())
                  .displayOrder(domain.getDisplayOrder())
                  .createdBy(domain.getCreatedBy())
                  .createdDate(domain.getCreatedDate())
                  .build();
      }

}