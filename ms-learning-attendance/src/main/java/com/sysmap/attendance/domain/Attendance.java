package com.sysmap.attendance.domain;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash("Attendance")
@Data
//@Builder
public class Attendance implements Serializable {

    private String id;

    private String attendanceId;

    private String courseId;

    @Indexed
    private String studentId;

    private LocalDateTime classDate;

    private boolean attendanceStatus;
}
