package com.sysmap.learning.course.service;

import com.sysmap.learning.course.data.ICourseRepository;
import com.sysmap.learning.course.domain.Course;
import com.sysmap.learning.course.mapper.CourseMapper;
import com.sysmap.learning.course.service.dto.CourseCreateResponse;
import com.sysmap.learning.course.service.dto.CourseFindByCourseIdResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private final ICourseRepository _courseRepository;
    private final CourseMapper _courseMapper;

    public CourseCreateResponse createCourse(Course course) {
        var courseId = UUID.randomUUID().toString();

        course.setCourseId(courseId);
        course.setStatus(true);
        course.setCreatedOn(LocalDateTime.now());

        _courseRepository.insert(course);

        return _courseMapper.entityToCreateResult(course);
    }

    public List<Course> findAll() {
        return _courseRepository.findAll();
    }

    public List<CourseFindByCourseIdResponse> findByCourseId(String courseId) {
        if(courseId == null){
            return  _courseRepository.findAll().stream()
                    .map(_courseMapper::entityToFindByCourseId)
                    .collect(Collectors.toList());
        }
        return _courseRepository.findByCourseId(courseId);
    }
}
