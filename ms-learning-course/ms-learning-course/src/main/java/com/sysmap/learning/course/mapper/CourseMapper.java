package com.sysmap.learning.course.mapper;

import com.sysmap.learning.course.controller.dto.CreateCourseInput;
import com.sysmap.learning.course.domain.Course;
import com.sysmap.learning.course.service.dto.CourseCreateResponse;
import com.sysmap.learning.course.service.dto.CourseFindByCourseIdResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
//@AllArgsConstructor
public class CourseMapper {

    private final ModelMapper modelMapper;

    public CourseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CourseCreateResponse entityToCreateResult(Course course) {
        return modelMapper.map(course, CourseCreateResponse.class);
    }

    public CourseFindByCourseIdResponse entityToFindByCourseId(Course course) {
        return modelMapper.map(course, CourseFindByCourseIdResponse.class);
    }

    public CourseCreateResponse entityToCreateResultOptional(Optional<Course> course) {
        return modelMapper.map(course, CourseCreateResponse.class);
    }

    public Course createCourseToEntity(CreateCourseInput input) {
        return modelMapper.map(input, Course.class);
    }

}
