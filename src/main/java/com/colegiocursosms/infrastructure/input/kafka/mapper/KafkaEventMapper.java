package com.colegiocursosms.infrastructure.input.kafka.mapper;

import com.colegiocursosms.domain.Enrollment;
import com.colegiocursosms.infrastructure.input.kafka.dto.EnrollmentCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventMapper {

      public Enrollment toDomain(EnrollmentCreatedEvent event) {
            return Enrollment.builder()
                  .id(event.getId())
                  .enrolled(event.getEnrolled())
                  .grade(event.getGrade())
                  .year(event.getYear())
                  .build();
      }
}