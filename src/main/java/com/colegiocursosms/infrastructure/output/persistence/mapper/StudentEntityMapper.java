package com.colegiocursosms.infrastructure.output.persistence.mapper;

import com.colegiocursosms.domain.Student;
import com.colegiocursosms.infrastructure.output.persistence.entity.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentEntityMapper {

      public Student toDomain(StudentEntity entity) {
            return Student.builder()
                  .id(entity.getId())
                  .name(entity.getName())
                  .surnamePaternal(entity.getSurnamePaternal())
                  .surnameMaternal(entity.getSurnameMaternal())
                  .fatherDni(entity.getFatherDni())
                  .motherDni(entity.getMotherDni())
                  .representativeDni(entity.getRepresentativeDni())
                  .build();
      }

      public StudentEntity toEntity(Student domain) {
            // El ID viene del evento, no se genera aquí
            return StudentEntity.builder()
                  .id(domain.getId())
                  .name(domain.getName())
                  .surnamePaternal(domain.getSurnamePaternal())
                  .surnameMaternal(domain.getSurnameMaternal())
                  .fatherDni(domain.getFatherDni())
                  .motherDni(domain.getMotherDni())
                  .representativeDni(domain.getRepresentativeDni())
                  .build();
      }

}