package com.sysmap.attendance.exception;

public class StudentNotFound extends RuntimeException{

    public StudentNotFound(String message){
        super(message);
    }
}
