package com.sysmap.attendance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseNotFound extends RuntimeException{

    public CourseNotFound(String message){
        super(message);
    }

}
