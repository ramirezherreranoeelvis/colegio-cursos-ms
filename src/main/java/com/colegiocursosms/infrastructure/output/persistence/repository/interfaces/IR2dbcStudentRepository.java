package com.colegiocursosms.infrastructure.output.persistence.repository.interfaces;

import com.colegiocursosms.infrastructure.output.persistence.entity.StudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IR2dbcStudentRepository extends ReactiveCrudRepository<StudentEntity, String> {}