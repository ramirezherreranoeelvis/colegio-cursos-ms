package com.colegiocursosms.infrastructure.input.rest.content.dto;

import com.colegiocursosms.domain.enums.ContentType;
import lombok.Builder;
import lombok.Data;

/**
 * DTO para la solicitud de actualización PARCIAL de un item de contenido.
 * Todos los campos son opcionales.
 */
@Data
@Builder
public class UpdateContentItemRequest {

      private String parentId;
      private ContentType itemType;
      private String title;
      private String dataPayload;
      private Integer displayOrder;
}