package com.sysmap.attendance.domain;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("Student")
@Data
public class Student {

    private String id;

    @Indexed
    private String studentId;

    private String fullName;

    private String courseId;
}
