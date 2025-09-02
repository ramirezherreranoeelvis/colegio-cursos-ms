package com.colegiocursosms.application.port.input.content;

import com.colegiocursosms.infrastructure.input.rest.content.dto.ContentItemResponse;
import reactor.core.publisher.Mono;
import java.util.List;

public interface IFindContentTreeUseCase {
      Mono<List<ContentItemResponse>> findTreeByScheduleId(String scheduleId);
}