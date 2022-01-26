package com.sysmap.learning.course.service;

import com.sysmap.learning.course.data.ICourseRepository;
import com.sysmap.learning.course.domain.Course;
import com.sysmap.learning.course.dto.response.CreateCourseResponse;
import com.sysmap.learning.course.dto.response.FindByCourseIdResponse;
import com.sysmap.learning.course.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final ICourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CreateCourseResponse createCourse(Course course) {
        var courseId = UUID.randomUUID();

        course.setCourseId(courseId.toString());
        course.setStatus(true);
        course.setCreatedOn(LocalDateTime.now());

        courseRepository.insert(course);

        return courseMapper.entityToCreateResult(course);
    }

    public List<FindByCourseIdResponse> findByCourseId(String courseId) {
        if (courseId == null) {
            return courseRepository.findAll().stream()
                    .map(courseMapper::entityToFindByCourseId)
                    .collect(Collectors.toList());
        }
        return courseRepository.findByCourseId(courseId);
    }

}
