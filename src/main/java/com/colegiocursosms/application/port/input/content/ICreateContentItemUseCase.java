package com.colegiocursosms.application.port.input.content;

import com.colegiocursosms.domain.CourseContentItem;
import reactor.core.publisher.Mono;

public interface ICreateContentItemUseCase {
      Mono<CourseContentItem> createContentItem(CourseContentItem contentItem);
}