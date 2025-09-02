package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.ICourseContentItemRepository;
import com.colegiocursosms.domain.CourseContentItem;
import com.colegiocursosms.infrastructure.output.persistence.mapper.CourseContentItemEntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcCourseContentItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class R2dbcCourseContentItemRepository implements ICourseContentItemRepository {

      private final IR2dbcCourseContentItemRepository r2dbcRepository;
      private final CourseContentItemEntityMapper mapper;

      @Override
      public Mono<CourseContentItem> save(CourseContentItem item) {
            if (item.getId() == null) {
                  item.setId(UUID.randomUUID().toString());
            }
            var entity = mapper.toEntity(item);
            entity.markNew();
            return r2dbcRepository.save(entity)
                  .map(mapper::toDomain);
      }

      @Override
      public Mono<CourseContentItem> findById(String id) {
            return r2dbcRepository.findById(id)
                  .map(mapper::toDomain);
      }

      @Override
      public Mono<List<CourseContentItem>> findAllByCourseScheduleIdAndParentId(String scheduleId, String parentId) {
            return r2dbcRepository.findAllByCourseScheduleIdAndParentIdOrderByDisplayOrderAsc(scheduleId, parentId)
                  .map(mapper::toDomain)
                  .collectList();
      }
      @Override
      public Mono<List<CourseContentItem>> findAllByCourseScheduleId(String scheduleId) {
            return r2dbcRepository.findAllByCourseScheduleIdOrderByDisplayOrderAsc(scheduleId)
                  .map(mapper::toDomain)
                  .collectList();
      }
}