package com.sysmap.attendance.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class RegisterAttendanceRequest {

    @NotNull
    private String courseId;

    @NotNull
    private boolean attendanceStatus;
}
