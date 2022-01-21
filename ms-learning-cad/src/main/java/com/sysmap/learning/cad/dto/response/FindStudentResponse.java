package com.sysmap.learning.cad.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class FindStudentResponse {

    private String fullName;

    private String document;

    private LocalDate birthDate;

    private List<String> courseName;

    private boolean status;
}
