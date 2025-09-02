package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.content.ICreateContentItemUseCase;
import com.colegiocursosms.application.port.input.content.IFindContentItemsUseCase;
import com.colegiocursosms.application.port.input.content.IUpdateContentItemUseCase;
import com.colegiocursosms.application.port.output.ICourseContentItemRepository;
import com.colegiocursosms.application.port.output.ICourseScheduleRepository;
import com.colegiocursosms.domain.CourseContentItem;
import com.colegiocursosms.domain.enums.ContentType;
import com.colegiocursosms.infrastructure.input.rest.dto.ContentItemResponse;
import com.colegiocursosms.infrastructure.input.rest.mapper.ContentItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ContentCourseService implements ICreateContentItemUseCase, IFindContentItemsUseCase, IUpdateContentItemUseCase {

      private final ICourseContentItemRepository contentItemRepository;
      private final ICourseScheduleRepository scheduleRepository;
      private final ContentItemMapper contentItemMapper;

      @Override
      public Mono<CourseContentItem> createContentItem(CourseContentItem contentItem) {
            // La misma lógica de validación y guardado que teníamos
            return scheduleRepository.existsById(contentItem.getCourseScheduleId())
                  .filter(Boolean.TRUE::equals)
                  .switchIfEmpty(Mono.error(new RuntimeException("El CourseSchedule con ID " + contentItem.getCourseScheduleId() + " no existe.")))
                  .then(Mono.defer(() -> {
                        if (contentItem.getParentId() != null) {
                              return contentItemRepository.findById(contentItem.getParentId())
                                    .switchIfEmpty(Mono.error(new RuntimeException("La carpeta padre con ID " + contentItem.getParentId() + " no existe.")))
                                    .filter(parent -> parent.getItemType() == ContentType.FOLDER)
                                    .switchIfEmpty(Mono.error(new RuntimeException("El padre con ID " + contentItem.getParentId() + " no es una carpeta.")))
                                    .then(contentItemRepository.save(contentItem));
                        }
                        return contentItemRepository.save(contentItem);
                  }));
      }

      @Override
      public Mono<List<ContentItemResponse>> findByScheduleIdAndParentId(String scheduleId, String parentId) {
            return contentItemRepository.findAllByCourseScheduleIdAndParentId(scheduleId, parentId)
                  .map(domainList ->
                        domainList.stream()
                              .map(contentItemMapper::toResponse)
                              .toList()
                  );
      }


      @Override
      public Mono<List<ContentItemResponse>> findTreeByScheduleId(String scheduleId) {
            return contentItemRepository.findAllByCourseScheduleId(scheduleId)
                  .map(domainList -> {
                        List<ContentItemResponse> dtoList = domainList.stream()
                              .map(contentItemMapper::toResponse)
                              .toList();

                        return buildTree(dtoList);
                  });
      }


      private List<ContentItemResponse> buildTree(List<ContentItemResponse> flatList) {
            Map<String, ContentItemResponse> map = flatList.stream()
                  .collect(Collectors.toMap(ContentItemResponse::getId, item -> item));

            List<ContentItemResponse> rootItems = new ArrayList<>();

            flatList.forEach(item -> {
                  if (item.getParentId() != null) {
                        ContentItemResponse parent = map.get(item.getParentId());
                        if (parent != null) {
                              parent.getChildren().add(item);
                        }
                  } else {
                        rootItems.add(item);
                  }
            });
            return rootItems;
      }


      @Override
      public Mono<CourseContentItem> updateContentItem(CourseContentItem contentItem) {
            return null;
      }

}
