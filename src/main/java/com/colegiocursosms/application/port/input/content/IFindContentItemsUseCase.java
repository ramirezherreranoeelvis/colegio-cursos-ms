package com.colegiocursosms.application.port.input.content;

import com.colegiocursosms.domain.CourseContentItem;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IFindContentItemsUseCase {

      Mono<List<CourseContentItem>> findByScheduleIdAndParentId(String scheduleId, String parentId);

}