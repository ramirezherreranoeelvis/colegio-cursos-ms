package com.colegiocursosms.infrastructure.output.persistence.mapper;

import com.colegiocursosms.domain.Teacher;
import com.colegiocursosms.infrastructure.output.persistence.entity.TeacherEntity;
import org.springframework.stereotype.Component;

@Component
public class TeacherEntityMapper {

      public Teacher toDomain(TeacherEntity entity) {
            return Teacher.builder()
                  .id(entity.getId())
                  .name(entity.getName())
                  .surnamePaternal(entity.getSurnamePaternal())
                  .surnameMaternal(entity.getSurnameMaternal())
                  .phone(entity.getPhone())
                  .build();
      }

      public TeacherEntity toEntity(Teacher domain) {
            return TeacherEntity.builder()
                  .id(domain.getId())
                  .name(domain.getName())
                  .surnamePaternal(domain.getSurnamePaternal())
                  .surnameMaternal(domain.getSurnameMaternal())
                  .phone(domain.getPhone())
                  .build();
      }

}