package com.sysmap.learning.course.controller;

import com.sysmap.learning.course.controller.dto.CreateCourseInput;
import com.sysmap.learning.course.domain.Course;
import com.sysmap.learning.course.mapper.CourseMapper;
import com.sysmap.learning.course.service.CourseService;
import com.sysmap.learning.course.service.dto.CourseCreateResponse;
import com.sysmap.learning.course.service.dto.CourseFindByCourseIdResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseCreateResponse createCourse(@RequestBody CreateCourseInput createCourseInput) {
        var course = courseMapper.createCourseToEntity(createCourseInput);
        return courseService.createCourse(course);
    }

    @GetMapping
    public ResponseEntity<List<CourseFindByCourseIdResponse>> findByCourseId(@RequestParam(required = false) String courseId) {
        var courses = courseService.findByCourseId(courseId);
        if(courses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courses);
    }

}
