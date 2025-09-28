package com.colegiocursosms.infrastructure.output.persistence.repository.interfaces;

import com.colegiocursosms.infrastructure.output.persistence.entity.CourseScheduleStudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IR2dbcCourseScheduleStudentRepository extends ReactiveCrudRepository<CourseScheduleStudentEntity, String> {
}