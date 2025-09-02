package com.colegiocursosms.infrastructure.input.rest.content.dto;

import com.colegiocursosms.domain.enums.ContentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateContentItemRequest {

      private String parentId;

      @NotNull(message = "El tipo de item es obligatorio")
      private ContentType itemType;

      @NotBlank(message = "El título no puede estar vacío")
      private String title;

      private String dataPayload;

      @NotNull(message = "El orden de visualización es obligatorio")
      private Integer displayOrder;

}