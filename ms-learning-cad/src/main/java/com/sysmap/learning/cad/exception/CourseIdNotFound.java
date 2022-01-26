package com.sysmap.learning.cad.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseIdNotFound extends RuntimeException {

    public CourseIdNotFound(String id) {
        super("Course not found with this courseId:" + id);
    }
}
