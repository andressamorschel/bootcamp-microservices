package com.sysmap.attendance.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.UUID;

@RedisHash("Attendance")
@Data
public class Attendance {

    @Id
    private String id;

    private UUID attendanceId;

    private String courseId;

    private String studentId;

    private LocalDateTime classDate;

    private boolean attendanceStatus;
}
