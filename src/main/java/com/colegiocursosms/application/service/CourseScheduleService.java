package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.content.ICreateContentItemUseCase;
import com.colegiocursosms.application.port.input.content.IFindContentItemsUseCase;
import com.colegiocursosms.application.port.input.courseschedule.IAssignTeacherToScheduleUseCase;
import com.colegiocursosms.application.port.input.courseschedule.IFindCourseSchedulesUseCase;
import com.colegiocursosms.application.port.input.courseschedule.IRegisterCoursesScheduleUseCase;
import com.colegiocursosms.application.port.output.ICourseContentItemRepository;
import com.colegiocursosms.application.port.output.ICourseScheduleRepository;
import com.colegiocursosms.application.port.output.ICourseScheduleTeacherRepository;
import com.colegiocursosms.application.port.output.ITeacherRepository;
import com.colegiocursosms.domain.CourseContentItem;
import com.colegiocursosms.domain.CourseSchedule;
import com.colegiocursosms.domain.CourseScheduleTeacher;
import com.colegiocursosms.domain.enums.AuditActionType;
import com.colegiocursosms.domain.enums.ContentType;
import com.colegiocursosms.domain.exception.CourseScheduleCodeAlreadyExistsException;
import com.colegiocursosms.domain.exception.TeacherAlreadyAssignedException;
import com.colegiocursosms.infrastructure.config.AuditingConfig;
import com.colegiocursosms.infrastructure.output.notification.kafka.CourseScheduleNotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CourseScheduleService implements IRegisterCoursesScheduleUseCase, IFindCourseSchedulesUseCase, IAssignTeacherToScheduleUseCase, ICreateContentItemUseCase, IFindContentItemsUseCase {

      private final ICourseScheduleRepository scheduleRepository;
      private final CourseScheduleNotificationProducer notificationProducer;
      private final ITeacherRepository teacherRepository;
      private final ICourseScheduleTeacherRepository assignmentRepository;
      private final ICourseContentItemRepository contentItemRepository;

      /**
       * Registra un nuevo horario, lo guarda en la base de datos y notifica
       * el evento a través de Kafka.
       */
      @Override
      public Mono<CourseSchedule> registerCourse(CourseSchedule courseSchedule) {
            log.info("Validando si ya existe un horario con el código: {}", courseSchedule.getCode());
            return scheduleRepository.existsByCode(courseSchedule.getCode())
                  .filter(Boolean.FALSE::equals)
                  .switchIfEmpty(Mono.error(new CourseScheduleCodeAlreadyExistsException(
                        "Ya existe un horario con el código '" + courseSchedule.getCode() + "'"
                  )))
                  .then(Mono.defer(() -> {
                        log.info("Código disponible. Guardando horario...");

                        AuditingConfig.setAuditor(AuditActionType.SELF_REGISTRATION.getValue());

                        return scheduleRepository.save(courseSchedule)
                              // 2. Añadimos el "efecto secundario" para notificar a Kafka
                              .doOnSuccess(savedSchedule -> {
                                    log.info("Horario guardado exitosamente con ID: {}. Enviando evento a Kafka...", savedSchedule.getId());
                                    // El .subscribe() aquí es crucial para activar el envío
                                    notificationProducer.sendCourseScheduledEvent(savedSchedule).subscribe();
                              })
                              .doFinally(signalType -> AuditingConfig.clearAuditor());
                  }));
      }

      @Override
      public Mono<CourseSchedule> findById(String id) {
            return scheduleRepository.findById(id);
      }

      @Override
      public Mono<CourseSchedule> findByCode(String code) {
            return scheduleRepository.findByCode(code);
      }

      @Override
      public Mono<List<CourseSchedule>> findAll() {
            return scheduleRepository.findAll();
      }

      @Override
      public Mono<CourseScheduleTeacher> assignTeacher(String scheduleId, String teacherId) {
            // 1. Validar que el horario y el profesor existan (como antes)
            Mono<Boolean> scheduleExists = scheduleRepository.existsById(scheduleId);
            Mono<Boolean> teacherExists = teacherRepository.existsById(teacherId);

            return Mono.zip(scheduleExists, teacherExists)
                  .flatMap(tuple -> {
                        if (!tuple.getT1()) return Mono.error(new RuntimeException("El horario no existe."));
                        if (!tuple.getT2()) return Mono.error(new RuntimeException("El profesor no existe."));

                        return assignmentRepository.existsByCourseScheduleIdAndTeacherId(scheduleId, teacherId);
                  })
                  .filter(Boolean.FALSE::equals)
                  .switchIfEmpty(Mono.error(new TeacherAlreadyAssignedException(
                        "El profesor con ID " + teacherId + " ya está asignado a este horario."
                  )))
                  .then(Mono.defer(() -> {
                        // 3. Si todas las validaciones pasan, crear y guardar
                        CourseScheduleTeacher newAssignment = CourseScheduleTeacher.builder()
                              .courseScheduleId(scheduleId)
                              .teacherId(teacherId)
                              .startDate(LocalDate.now())
                              .role("PRINCIPAL")
                              .build();

                        AuditingConfig.setAuditor(AuditActionType.ADMIN.getValue());
                        return assignmentRepository.save(newAssignment)
                              .doFinally(signalType -> AuditingConfig.clearAuditor());
                  }));
      }

      @Override
      public Mono<CourseContentItem> createContentItem(CourseContentItem contentItem) {
            // 1. Validar que el CourseSchedule al que pertenece, exista.
            return scheduleRepository.existsById(contentItem.getCourseScheduleId())
                  .filter(Boolean.TRUE::equals)
                  .switchIfEmpty(Mono.error(new RuntimeException("El CourseSchedule con ID " + contentItem.getCourseScheduleId() + " no existe.")))
                  .then(Mono.defer(() -> {
                        // 2. Si tiene un padre, validar que el padre exista y sea una carpeta
                        if (contentItem.getParentId() != null) {
                              return contentItemRepository.findById(contentItem.getParentId())
                                    .switchIfEmpty(Mono.error(new RuntimeException("La carpeta padre con ID " + contentItem.getParentId() + " no existe.")))
                                    .filter(parent -> parent.getItemType() == ContentType.FOLDER)
                                    .switchIfEmpty(Mono.error(new RuntimeException("El padre con ID " + contentItem.getParentId() + " no es una carpeta.")))
                                    .then(contentItemRepository.save(contentItem));
                        }
                        // 3. Si no tiene padre, guardar directamente
                        return contentItemRepository.save(contentItem);
                  }));
      }

      @Override
      public Mono<List<CourseContentItem>> findByScheduleIdAndParentId(String scheduleId, String parentId) {
            return contentItemRepository.findAllByCourseScheduleIdAndParentId(scheduleId, parentId);
      }
}