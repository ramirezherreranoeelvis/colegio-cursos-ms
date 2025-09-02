package com.colegiocursosms.infrastructure.output.persistence.repository.interfaces;

import com.colegiocursosms.infrastructure.output.persistence.entity.CourseContentItemEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IR2dbcCourseContentItemRepository extends ReactiveCrudRepository<CourseContentItemEntity, String> {

      /**
       * Busca todos los items de contenido que pertenecen a un horario y a un padre específico.
       * Si parentId es null, busca los elementos de la raíz.
       *
       * @return Un Flux (0 a N elementos) de las entidades encontradas.
       */
      Flux<CourseContentItemEntity> findAllByCourseScheduleIdAndParentIdOrderByDisplayOrderAsc(String courseScheduleId, String parentId);

      Flux<CourseContentItemEntity> findAllByCourseScheduleIdOrderByDisplayOrderAsc(String courseScheduleId);

}