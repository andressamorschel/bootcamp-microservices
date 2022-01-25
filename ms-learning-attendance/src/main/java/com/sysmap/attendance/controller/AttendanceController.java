package com.sysmap.attendance.controller;

import com.sysmap.attendance.client.MsLearningCourseClient;
import com.sysmap.attendance.domain.Attendance;
import com.sysmap.attendance.dto.request.RegisterAttendanceRequest;
import com.sysmap.attendance.dto.response.GetAttendancesResponse;
import com.sysmap.attendance.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final MsLearningCourseClient msLearningCourseClient;

    public AttendanceController(AttendanceService attendanceService, MsLearningCourseClient msLearningCourseClient) {
        this.attendanceService = attendanceService;
        this.msLearningCourseClient = msLearningCourseClient;
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<Void> registerAttendance(@PathVariable String studentId, @RequestBody
            RegisterAttendanceRequest registerAttendanceRequest) {
        attendanceService.createAttendance(registerAttendanceRequest, studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public String teste() {
        var oi = msLearningCourseClient
                .getStudent("77c04ff2-8b5e-4d43-aa78-2cd363f4d611");
        if(oi.isEmpty()){
            return "deu ruim";
        }

        return "deu bom";
    }

    @GetMapping("/{studentId}")
    public Optional<GetAttendancesResponse> findAttendancesByStudent(@PathVariable String studentId){
        return attendanceService.getAttendances(studentId);
    }
}
