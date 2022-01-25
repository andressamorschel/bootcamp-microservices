package com.sysmap.attendance.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Student")
@Data
public class Student {

    @Id
    private String id;

    private String studentId;

    private String fullName;

    private String courseId;
}
