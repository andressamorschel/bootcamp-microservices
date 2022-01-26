package com.sysmap.learning.cad.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private String id;

    @Indexed(unique = true)
    private String studentId;

    private String firstName;

    private String lastName;

    private String document;

    private LocalDate birthDate;

    private String courseId;

    private boolean status;

    private LocalDateTime createdOn;

}
