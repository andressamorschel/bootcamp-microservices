package com.sysmap.learning.course.dto.response;

import lombok.Data;

@Data
public class FindByCourseIdResponse {

    private String courseId;

    private String courseName;

    private boolean status;
}
