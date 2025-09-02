package com.colegiocursosms.infrastructure.input.rest;

import com.colegiocursosms.application.port.input.content.ICreateContentItemUseCase;
import com.colegiocursosms.application.port.input.content.IFindContentItemsUseCase;
import com.colegiocursosms.application.port.input.content.IFindContentTreeUseCase;
import com.colegiocursosms.domain.CourseContentItem;
import com.colegiocursosms.infrastructure.input.rest.dto.ContentItemResponse;
import com.colegiocursosms.infrastructure.input.rest.dto.CreateContentItemRequest;
import com.colegiocursosms.infrastructure.input.rest.mapper.ContentItemMapper;
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
      private final IFindContentTreeUseCase findContentTreeUseCase;
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
                  .map(itemList -> itemList.stream()
                        .map(mapper::toResponse)
                        .toList()
                  )
                  .map(ResponseEntity::ok);
      }

      @GetMapping("/tree")
      public Mono<ResponseEntity<List<ContentItemResponse>>> findContentTree(
            @PathVariable String scheduleId) {

            return findContentTreeUseCase.findTreeByScheduleId(scheduleId)
                  .map(ResponseEntity::ok);
      }

}