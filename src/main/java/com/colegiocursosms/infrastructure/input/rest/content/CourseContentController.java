package com.colegiocursosms.infrastructure.input.rest.content;

import com.colegiocursosms.application.port.input.content.ICreateContentItemUseCase;
import com.colegiocursosms.application.port.input.content.IFindContentItemsUseCase;
import com.colegiocursosms.application.port.input.content.IUpdateContentItemUseCase;
import com.colegiocursosms.domain.CourseContentItem;
import com.colegiocursosms.infrastructure.input.rest.content.dto.ContentItemResponse;
import com.colegiocursosms.infrastructure.input.rest.content.dto.CreateContentItemRequest;
import com.colegiocursosms.infrastructure.input.rest.content.dto.UpdateContentItemRequest;
import com.colegiocursosms.infrastructure.input.rest.content.mapper.ContentItemMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/course-schedules/{scheduleId}/contents")
@RequiredArgsConstructor
public class CourseContentController {

      private final ICreateContentItemUseCase createContentItemUseCase;
      private final IFindContentItemsUseCase findContentItemsUseCase;
      private final IUpdateContentItemUseCase updateContentItemUseCase;
      private final ContentItemMapper mapper;

      @PostMapping("")
      public Mono<ResponseEntity<ContentItemResponse>> createContentItem(
            @PathVariable String scheduleId,
            @Valid @RequestBody CreateContentItemRequest request) {

            CourseContentItem newItem = mapper.toDomain(request);
            newItem.setCourseScheduleId(scheduleId);

            return createContentItemUseCase.createContentItem(newItem)
                  .map(mapper::toResponse)
                  .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
      }

      @GetMapping("")
      public Mono<ResponseEntity<List<ContentItemResponse>>> findContentItems(
            @PathVariable String scheduleId,
            @RequestParam(required = false) String parentId) {

            return findContentItemsUseCase.findByScheduleIdAndParentId(scheduleId, parentId)
                  .map(ResponseEntity::ok);
      }

      @GetMapping("/tree")
      public Mono<ResponseEntity<List<ContentItemResponse>>> findContentTree(
            @PathVariable String scheduleId) {

            return findContentItemsUseCase.findTreeByScheduleId(scheduleId)
                  .map(ResponseEntity::ok);
      }

      @PatchMapping("/{contentId}")
      public Mono<ResponseEntity<ContentItemResponse>> updateContentItem(
            @PathVariable String scheduleId,
            @PathVariable String contentId,
            @Valid @RequestBody UpdateContentItemRequest request) {

            CourseContentItem updatedItem = mapper.toDomain(request);
            updatedItem.setId(contentId);

            return updateContentItemUseCase.updateContentItem(updatedItem)
                  .map(mapper::toResponse)
                  .map(ResponseEntity::ok);
      }
}