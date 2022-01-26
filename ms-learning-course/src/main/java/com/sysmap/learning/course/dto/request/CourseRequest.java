package com.sysmap.learning.course.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CourseRequest {

    @NotBlank
    private String courseName;
}
