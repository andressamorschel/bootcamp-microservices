package com.sysmap.learning.cad.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatedStudentEvent {

    private String studentId;

    private String fullName;

    private String courseId;

}
