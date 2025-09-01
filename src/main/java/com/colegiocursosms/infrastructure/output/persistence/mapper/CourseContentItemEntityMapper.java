package com.colegiocursosms.infrastructure.output.persistence.mapper;

import com.colegiocursosms.domain.CourseContentItem;
import com.colegiocursosms.infrastructure.output.persistence.entity.CourseContentItemEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseContentItemEntityMapper {

      /**
       * Convierte una entidad de persistencia a un objeto de dominio.
       * @param entity El objeto recuperado de la base de datos.
       * @return Un objeto de negocio puro.
       */
      public CourseContentItem toDomain(CourseContentItemEntity entity) {
            return CourseContentItem.builder()
                  .id(entity.getId())
                  .courseScheduleId(entity.getCourseScheduleId())
                  .parentId(entity.getParentId())
                  .itemType(entity.getItemType())
                  .title(entity.getTitle())
                  .dataPayload(entity.getDataPayload())
                  .displayOrder(entity.getDisplayOrder())
                  .createdBy(entity.getCreatedBy())
                  .createdDate(entity.getCreatedDate())
                  .lastModifiedBy(entity.getLastModifiedBy())
                  .lastModifiedDate(entity.getLastModifiedDate())
                  .build();
      }

      /**
       * Convierte un objeto de dominio a su correspondiente entidad de persistencia.
       * @param domain El objeto de negocio.
       * @return Una entidad lista para ser guardada en la base de datos.
       */
      public CourseContentItemEntity toEntity(CourseContentItem domain) {
            return CourseContentItemEntity.builder()
                  .id(domain.getId())
                  .courseScheduleId(domain.getCourseScheduleId())
                  .parentId(domain.getParentId())
                  .itemType(domain.getItemType())
                  .title(domain.getTitle())
                  .dataPayload(domain.getDataPayload())
                  .displayOrder(domain.getDisplayOrder())
                  .createdBy(domain.getCreatedBy())
                  .createdDate(domain.getCreatedDate())
                  .lastModifiedBy(domain.getLastModifiedBy())
                  .lastModifiedDate(domain.getLastModifiedDate())
                  .build();
      }
}