package com.colegiocursosms.application.port.input.content;

import com.colegiocursosms.domain.CourseContentItem;
import reactor.core.publisher.Mono;

public interface IUpdateContentItemUseCase {
      /**
       * Actualiza un item de contenido existente.
       * @param contentItem El objeto de contenido con los datos actualizados.
       * @return Un Mono que emite el item de contenido actualizado.
       */
      Mono<CourseContentItem> updateContentItem(CourseContentItem contentItem);
}