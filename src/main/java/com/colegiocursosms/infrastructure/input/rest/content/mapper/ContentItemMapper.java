package com.colegiocursosms.infrastructure.input.rest.mapper;

import com.colegiocursosms.domain.CourseContentItem;
import com.colegiocursosms.infrastructure.input.rest.dto.ContentItemResponse;
import com.colegiocursosms.infrastructure.input.rest.dto.CreateContentItemRequest;
import com.colegiocursosms.infrastructure.input.rest.dto.UpdateContentItemRequest;
import org.springframework.stereotype.Component;

@Component
public class ContentItemMapper {

      /**
       * Convierte un DTO de CREACIÓN a un objeto de dominio.
       */
      public CourseContentItem toDomain(CreateContentItemRequest request) {
            return CourseContentItem.builder()
                  .parentId(request.getParentId())
                  .itemType(request.getItemType())
                  .title(request.getTitle())
                  .dataPayload(request.getDataPayload())
                  .displayOrder(request.getDisplayOrder())
                  .build();
      }

      /**
       * Convierte un DTO de ACTUALIZACIÓN a un objeto de dominio.
       * Este objeto puede tener campos nulos.
       */
      public CourseContentItem toDomain(UpdateContentItemRequest request) {
            return CourseContentItem.builder()
                  .parentId(request.getParentId())
                  .itemType(request.getItemType())
                  .title(request.getTitle())
                  .dataPayload(request.getDataPayload())
                  .displayOrder(request.getDisplayOrder())
                  .build();
      }

      /**
       * Convierte un objeto de dominio a un DTO de RESPUESTA para la API.
       */
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