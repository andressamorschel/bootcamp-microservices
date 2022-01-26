package com.sysmap.attendance.mapper;

import com.sysmap.attendance.domain.Attendance;
import com.sysmap.attendance.dto.response.GetAttendancesResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttendanceMapper {

    private final ModelMapper modelMapper;

    public GetAttendancesResponse toGetAttendancesResponse(Attendance attendance) {
        return modelMapper.map(attendance, GetAttendancesResponse.class);
    }
}
