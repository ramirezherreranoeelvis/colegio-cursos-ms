package com.colegiocursosms.infrastructure.output.notification.mapper;

import com.colegiocursosms.application.port.input.course.IFindCoursesUseCase;
import com.colegiocursosms.domain.CourseSchedule;
import com.colegiocursosms.infrastructure.output.notification.dto.CourseScheduleCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CourseScheduleMapper {
      private final IFindCoursesUseCase findCoursesUseCase;

      public CourseScheduleCreatedEvent toProducer(CourseSchedule courseSchedule){
            return this.findCoursesUseCase.
      }
}
