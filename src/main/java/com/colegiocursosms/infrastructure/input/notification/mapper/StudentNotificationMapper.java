package com.colegiocursosms.infrastructure.input.notification.mapper;

import com.colegiocursosms.domain.Student;
import com.colegiocursosms.infrastructure.input.notification.dto.StudentCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class StudentNotificationMapper {
      public Student toDomain(StudentCreatedEvent dto) {
            return Student.builder()
                  .id(dto.getId())
                  .name(dto.getName())
                  .surnamePaternal(dto.getSurnamePaternal())
                  .surnameMaternal(dto.getSurnameMaternal())
                  .fatherDni(dto.getFatherDni())
                  .motherDni(dto.getMotherDni())
                  .representativeDni(dto.getRepresentativeDni())
                  .build();
      }
}