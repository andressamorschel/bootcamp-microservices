package com.sysmap.attendance.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {

    private String fullName;

    private List<String> courseName;

}
