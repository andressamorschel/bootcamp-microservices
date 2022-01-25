package com.sysmap.attendance.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Attendances {

    private String courseId;

    private LocalDateTime classDate;

    private boolean attendanceStatus;

}
