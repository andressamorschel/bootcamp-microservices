package com.sysmap.attendance.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterAttendanceRequest {

    private String courseId;

    private boolean attendanceStatus;
}
