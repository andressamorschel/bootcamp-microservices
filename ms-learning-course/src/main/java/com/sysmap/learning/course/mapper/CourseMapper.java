package com.sysmap.learning.course.mapper;

import com.sysmap.learning.course.domain.Course;
import com.sysmap.learning.course.dto.request.CourseRequest;
import com.sysmap.learning.course.dto.response.CreateCourseResponse;
import com.sysmap.learning.course.dto.response.FindByCourseIdResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CourseMapper {

    private ModelMapper modelMapper;

    public CreateCourseResponse entityToCreateResult(Course course) {
        return modelMapper.map(course, CreateCourseResponse.class);
    }

    public FindByCourseIdResponse entityToFindByCourseId(Course course) {
        return modelMapper.map(course, FindByCourseIdResponse.class);
    }

    public Course createCourseToEntity(CourseRequest request) {
        return modelMapper.map(request, Course.class);
    }

}