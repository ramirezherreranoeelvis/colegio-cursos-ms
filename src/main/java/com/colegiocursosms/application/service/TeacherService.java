package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.teacher.IFindTeachersUseCase;
import com.colegiocursosms.application.port.input.teacher.IProcessTeacherEventUseCase;
import com.colegiocursosms.application.port.output.ITeacherRepository;
import com.colegiocursosms.domain.Teacher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Implementación de los casos de uso para la gestión de la réplica
 * de datos de Profesores.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class TeacherService implements IProcessTeacherEventUseCase, IFindTeachersUseCase {

      private final ITeacherRepository teacherRepository;

      /**
       * Procesa un evento de profesor, decidiendo si debe insertar un nuevo
       * registro o actualizar uno existente basado en su ID.
       */
      @Override
      public Mono<Teacher> processTeacherEvent(Teacher teacher) {
            log.info("Procesando evento para el profesor con ID: {}", teacher.getId());

            return teacherRepository.existsById(teacher.getId())
                  .flatMap(exists -> {
                        if (Boolean.TRUE.equals(exists)) {
                              log.info("La réplica del profesor {} ya existe. Actualizando...", teacher.getId());
                              return teacherRepository.update(teacher);
                        } else {
                              log.info("La réplica del profesor {} es nueva. Insertando...", teacher.getId());
                              return teacherRepository.insert(teacher);
                        }
                  });
      }

      @Override
      public Mono<Teacher> findById(String id) {
            return teacherRepository.findById(id);
      }

      @Override
      public Mono<List<Teacher>> findAll() {
            return teacherRepository.findAll();
      }

}