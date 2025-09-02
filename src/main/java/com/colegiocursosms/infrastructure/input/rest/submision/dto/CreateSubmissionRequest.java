package com.colegiocursosms.infrastructure.input.rest.submision.dto;

import com.colegiocursosms.domain.enums.SubmissionType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSubmissionRequest {

      @NotNull(message = "El tipo de entrega es obligatorio")
      private SubmissionType submissionType;

      // El contenido de la entrega (ej. un JSON con la ruta a un archivo o texto)
      private String submissionContent;
}