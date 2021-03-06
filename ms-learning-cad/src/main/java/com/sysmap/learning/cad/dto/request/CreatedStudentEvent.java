package com.sysmap.learning.cad.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreatedStudentEvent {

    private String studentId;

    private String fullName;

    private String courseId;

}
