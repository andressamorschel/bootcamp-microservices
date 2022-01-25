package com.sysmap.learning.cad.dto.request;

import lombok.Data;

@Data
public class CreatedStudentEvent {

    private String studentId;

    private String fullName;

    private String courseId;

    public CreatedStudentEvent(String studentId, String fullName, String courseId) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.courseId = courseId;
    }

    public CreatedStudentEvent() {
    }
}
