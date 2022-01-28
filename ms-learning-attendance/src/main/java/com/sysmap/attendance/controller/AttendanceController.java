package com.sysmap.attendance.controller;

import com.sysmap.attendance.dto.request.RegisterAttendanceRequest;
import com.sysmap.attendance.dto.response.GetAttendancesResponse;
import com.sysmap.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/{studentId}")
    public ResponseEntity<Void> registerAttendance(@PathVariable String studentId, @RequestBody
    @Valid RegisterAttendanceRequest registerAttendanceRequest) {
        attendanceService.createAttendance(registerAttendanceRequest, studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{studentId}")
    public GetAttendancesResponse findAttendancesByStudent(@PathVariable String studentId) {
        return attendanceService.getAttendances2(studentId);
    }
}
