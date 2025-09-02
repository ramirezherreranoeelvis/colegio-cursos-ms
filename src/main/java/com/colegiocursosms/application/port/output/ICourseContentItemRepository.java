package com.colegiocursosms.application.port.output;

import com.colegiocursosms.domain.CourseContentItem;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICourseContentItemRepository {

      Mono<CourseContentItem> save(CourseContentItem item);

      Mono<CourseContentItem> findById(String id);

      Mono<List<CourseContentItem>> findAllByCourseScheduleIdAndParentId(String scheduleId, String parentId);

      Mono<List<CourseContentItem>> findAllByCourseScheduleId(String scheduleId);
}