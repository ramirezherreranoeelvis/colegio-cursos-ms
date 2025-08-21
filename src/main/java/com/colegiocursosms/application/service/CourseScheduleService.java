package com.colegiocursosms.application.service;

import com.colegiocursosms.application.port.input.courseschedule.IFindCourseSchedulesUseCase;
import com.colegiocursosms.application.port.input.courseschedule.IFindCourseSchedulesUseCase;
import com.colegiocursosms.application.port.input.courseschedule.IRegisterCoursesScheduleUseCase;
import com.colegiocursosms.application.port.output.ICourseScheduleRepository;
import com.colegiocursosms.domain.CourseSchedule;
import com.colegiocursosms.domain.enums.AuditActionType;
import com.colegiocursosms.domain.exception.CourseScheduleCodeAlreadyExistsException;
import com.colegiocursosms.infrastructure.config.AuditingConfig;
import com.colegiocursosms.infrastructure.output.notification.kafka.CourseScheduleNotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CourseScheduleService implements IRegisterCoursesScheduleUseCase, IFindCourseSchedulesUseCase {

      private final ICourseScheduleRepository scheduleRepository;
      private final CourseScheduleNotificationProducer notificationProducer;

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

}