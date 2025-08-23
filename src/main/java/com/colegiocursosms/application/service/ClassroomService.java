package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.classroom.IFindClassroomsUseCase;
import com.colegiocursosms.application.port.input.classroom.IRegisterClassroomUseCase;
import com.colegiocursosms.application.port.output.IClassroomRepository;
import com.colegiocursosms.domain.enums.AuditActionType;
import com.colegiocursosms.domain.Classroom;
import com.colegiocursosms.domain.exception.ClassroomNumberAlreadyExistsException;
import com.colegiocursosms.infrastructure.config.AuditingConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Implementación de los casos de uso para la gestión de Aulas.
 * Orquesta la lógica de negocio y se comunica con la capa de persistencia
 * a través de los puertos de salida.
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class ClassroomService implements IFindClassroomsUseCase, IRegisterClassroomUseCase {

      private final IClassroomRepository classroomRepository;

      @Override
      public Mono<List<Classroom>> findAll() {
            return classroomRepository.findAll();
      }

      /**
       * Registra una nueva aula después de validar que el número no esté en uso.
       * 1. Verifica si el número de aula ya existe.
       * 2. Si existe, lanza una ClassroomNumberAlreadyExistsException.
       * 3. Si no existe, establece el contexto de auditoría y delega el guardado
       * al repositorio, asegurando la limpieza del auditor al finalizar.
       */
      @Override
      public Mono<Classroom> registerClassroom(Classroom classroom) {
            log.info("Iniciando validación para registrar el aula número: {}", classroom.getNumber());

            return classroomRepository.existsByNumber(classroom.getNumber())
                  .filter(exists -> !exists)
                  .switchIfEmpty(Mono.error(new ClassroomNumberAlreadyExistsException(
                        "El aula con el número '" + classroom.getNumber() + "' ya está registrada."
                  )))
                  .then(Mono.defer(() -> {
                        log.info("El número '{}' está disponible. Guardando aula...", classroom.getNumber());

                        // Establecemos el auditor para la transacción
                        AuditingConfig.setAuditor(AuditActionType.SELF_REGISTRATION.getValue());

                        return classroomRepository.save(classroom)
                              .doFinally(signalType -> AuditingConfig.clearAuditor());
                  }));
      }

      @Override
      public Mono<Classroom> findById(String id) {
            log.info("Buscando aula por ID: {}", id);
            return classroomRepository.findById(id);
      }
}
