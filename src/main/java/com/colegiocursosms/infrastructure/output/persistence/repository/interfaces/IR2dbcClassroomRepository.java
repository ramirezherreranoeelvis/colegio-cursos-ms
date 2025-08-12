package com.colegiocursosms.infrastructure.output.persistence.repository.interfaces;

import com.colegiocursosms.infrastructure.output.persistence.entity.ClassRoomEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Interfaz de repositorio de Spring Data para la entidad ClassRoomEntity.
 * Proporciona métodos CRUD reactivos y consultas derivadas.
 */
@Repository
public interface IR2dbcClassroomRepository extends ReactiveCrudRepository<ClassRoomEntity, String> {

      /**
       * Verifica si existe un aula por su número. Spring Data implementa
       * este método basado en su nombre.
       */
      Mono<Boolean> existsByNumber(Integer number);

      /**
       * Busca un aula por su número. Spring Data implementa este
       * método basado en su nombre.
       */
      Mono<ClassRoomEntity> findByNumber(Integer number);
}