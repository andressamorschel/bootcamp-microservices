package com.sysmap.attendance.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAttendancesResponse {

    private String fullName;

    private List<String> courseName;

    private List<Attendances> attendances;

}
