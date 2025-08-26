package com.colegiocursosms.infrastructure.output.persistence.repository;

import com.colegiocursosms.application.port.output.ICourseScheduleTeacherRepository;
import com.colegiocursosms.domain.CourseScheduleTeacher;
import com.colegiocursosms.infrastructure.output.persistence.mapper.CourseScheduleTeacherEntityMapper;
import com.colegiocursosms.infrastructure.output.persistence.repository.interfaces.IR2dbcCourseScheduleTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class R2dbcCourseScheduleTeacherRepository implements ICourseScheduleTeacherRepository {

      private final IR2dbcCourseScheduleTeacherRepository r2dbcRepository;
      private final CourseScheduleTeacherEntityMapper mapper;

      /**
       * Guarda (inserta) una nueva asignación en la base de datos.
       */
      @Override
      public Mono<CourseScheduleTeacher> save(CourseScheduleTeacher assignmentDomain) {
            if (assignmentDomain.getId() == null) {
                  assignmentDomain.setId(UUID.randomUUID().toString());
            }
            var entity = mapper.toEntity(assignmentDomain);
            entity.markNew(); // Forzar un INSERT
            return r2dbcRepository.save(entity)
                  .map(mapper::toDomain);
      }

      /**
       * Devuelve una lista de todas las asignaciones para un horario de curso específico.
       */
      @Override
      public Mono<List<CourseScheduleTeacher>> findAllByCourseScheduleId(String courseScheduleId) {
            return r2dbcRepository.findAllByCourseScheduleId(courseScheduleId)
                  .map(mapper::toDomain)
                  .collectList();
      }
}