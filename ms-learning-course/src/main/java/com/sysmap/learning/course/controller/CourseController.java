package com.sysmap.learning.course.controller;

import com.sysmap.learning.course.dto.request.CourseRequest;
import com.sysmap.learning.course.dto.response.CreateCourseResponse;
import com.sysmap.learning.course.dto.response.FindByCourseIdResponse;
import com.sysmap.learning.course.mapper.CourseMapper;
import com.sysmap.learning.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCourseResponse createCourse(@RequestBody @Valid CourseRequest createCourseInput) {
        var course = courseMapper.createCourseToEntity(createCourseInput);
        return courseService.createCourse(course);
    }

    @GetMapping
    public ResponseEntity<List<FindByCourseIdResponse>> findByCourseId(
            @RequestParam(required = false) String courseId) {
        var courses = courseService.findByCourseId(courseId);
        if (courses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courses);
    }

}