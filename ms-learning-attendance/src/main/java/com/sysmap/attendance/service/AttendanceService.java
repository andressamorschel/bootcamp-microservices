package com.sysmap.attendance.service;

import com.sysmap.attendance.client.MsLearningCourseClient;
import com.sysmap.attendance.data.AttendanceRepository;
import com.sysmap.attendance.domain.Attendance;
import com.sysmap.attendance.domain.Student;
import com.sysmap.attendance.dto.request.RegisterAttendanceRequest;
import com.sysmap.attendance.dto.response.Attendances;
import com.sysmap.attendance.dto.response.GetAttendancesResponse;
import com.sysmap.attendance.exception.CourseNotFound;
import com.sysmap.attendance.exception.StudentNotFound;
import com.sysmap.attendance.mapper.AttendanceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final StudentService studentService;
    private final MsLearningCourseClient msLearningCourseClient;

    public void createAttendance(RegisterAttendanceRequest registerAttendanceRequest, String studentId) {

        var courseId = registerAttendanceRequest.getCourseId();
        var find = attendanceRepository.findById("7cf47946-88d7-44ac-867e-1982aa25af63");
        log.info("find {}", find);
        var responseStudent = studentService.findStudentById(studentId);
        log.info("ola mundo: {}", responseStudent);

        if (studentService.findStudentById(studentId)) {
            throw new StudentNotFound("student not found with id:" + studentId);
        } else if (!msLearningCourseClient.courseExist(courseId)) {
            throw new CourseNotFound("course not found with id:" + courseId);
        }


        var attendance = new Attendance();
        attendance.setAttendanceId(UUID.randomUUID().toString());
        attendance.setClassDate(LocalDateTime.now());
        attendance.setCourseId(courseId);
        attendance.setStudentId(studentId);
        attendance.setAttendanceStatus(registerAttendanceRequest.isAttendanceStatus());

        attendanceRepository.save(attendance);
    }

    public Optional<GetAttendancesResponse> getAttendances(String studentId) {
        var oi = attendanceRepository.findAll();
        log.info("findAll {}", oi);
        var findAttendance = attendanceRepository
                .findByStudentId(studentId);
        var chinoDouglas = attendanceRepository.findById("0b4bab67-495c-4b18-8759-f8ab3f8d7460");

        log.info("chinoDouglas {}", chinoDouglas);
        log.info("findAttendance {}", findAttendance);

//        if(findAttendance.isEmpty()){
//            return Optional.empty();
//        }

        var attendances = Attendances.builder()
                .courseId(findAttendance.getCourseId())
                .classDate(findAttendance.getClassDate())
                .attendanceStatus(findAttendance.isAttendanceStatus())
                .build();

        log.info("attendances {}", attendances);

        var student = msLearningCourseClient.getStudent(studentId).get();

        log.info("student {}", student);

        var response = GetAttendancesResponse.builder()
                .fullName(student.getFullName())
                .attendances(List.of(attendances))
                .courseName(student.getCourseName())
                .build();

        log.info("response {}", response);

        return Optional.of(response);
    }

}
