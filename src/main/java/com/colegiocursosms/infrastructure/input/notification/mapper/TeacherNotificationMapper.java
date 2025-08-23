package com.colegiocursosms.infrastructure.input.notification.mapper;

import com.colegiocursosms.domain.Teacher;
import com.colegiocursosms.infrastructure.input.notification.dto.TeacherCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class TeacherNotificationMapper {

      public Teacher toDomain(TeacherCreatedEvent dto) {
            return Teacher.builder()
                  .id(dto.getId())
                  .name(dto.getName())
                  .surnamePaternal(dto.getSurnamePaternal())
                  .surnameMaternal(dto.getSurnameMaternal())
                  .phone(dto.getPhone())
                  .build();
      }
}