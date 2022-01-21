package com.sysmap.learning.course.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Data
public class Course {

    @Id
    private String id;

    @Indexed(unique = true)
    private String courseId;

    private String courseName;

    private boolean status;

    private LocalDateTime createdOn;

}
