package com.sysmap.learning.cad.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentRequest {

    private String firstName;

    private String lastName;

    private String document;

    private LocalDateTime birthDate;

    private String courseId;

}
