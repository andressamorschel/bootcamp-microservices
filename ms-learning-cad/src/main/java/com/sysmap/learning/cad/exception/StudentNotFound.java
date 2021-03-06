package com.sysmap.learning.cad.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFound extends RuntimeException {

    public StudentNotFound(String message) {
        super(message);
    }

}
