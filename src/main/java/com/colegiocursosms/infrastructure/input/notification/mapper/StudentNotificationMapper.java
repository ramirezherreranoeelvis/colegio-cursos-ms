package com.colegioenrollmentms.infrastructure.input.notification.mapper;

import com.colegioenrollmentms.domain.Student;
import com.colegioenrollmentms.infrastructure.input.notification.dto.StudentCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class StudentNotificationMapper {
      public Student toDomain(StudentCreatedEvent dto) {
            return Student.builder()
                  .id(dto.getId())
                  .dni(dto.getDni())
                  .name(dto.getName())
                  .surnamePaternal(dto.getSurnamePaternal())
                  .surnameMaternal(dto.getSurnameMaternal())
                  .fatherId(dto.getFatherId())
                  .motherId(dto.getMotherId())
                  .representativeId(dto.getRepresentativeId())
                  .build();
      }
}