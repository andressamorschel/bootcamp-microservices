package com.sysmap.learning.course.service.dto;

import lombok.Data;

@Data
public class CourseFindByCourseIdResponse {

    private String courseId;

    private String courseName;

    private boolean status;
}
