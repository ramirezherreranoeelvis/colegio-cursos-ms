package com.colegiocursosms.infrastructure.input.notification.mapper;

import com.colegiocursosms.domain.Enrollment;
import com.colegiocursosms.infrastructure.input.notification.dto.EnrollmentCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentConsumerMapper {

      public Enrollment toDomain(EnrollmentCreatedEvent event) {
            return Enrollment.builder()
                  .id(event.getId())
                  .enrolled(event.getEnrolled())
                  .grade(event.getGrade())
                  .year(event.getYear())
                  .periodType(event.getPeriodType())
                  .build();
      }
}