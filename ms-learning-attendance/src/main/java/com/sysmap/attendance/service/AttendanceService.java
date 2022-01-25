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

        var responseStudent = studentService.findStudentById(studentId);
        log.info("ola mundo: {}", responseStudent);

        if (studentService.findStudentById(studentId)) {
            throw new StudentNotFound("student not found with id:" + studentId);
        } else if (!msLearningCourseClient.courseExist(courseId)) {
            throw new CourseNotFound("course not found with id:" + courseId);
        }

        var attendance = attendanceMapper.registerAttendanceToEntity(registerAttendanceRequest);
        attendance.setClassDate(LocalDateTime.now());
        attendance.setStudentId(studentId);

        attendanceRepository.save(attendance);
    }

    public Optional<GetAttendancesResponse> getAttendances(String studentId) {
        var findAttendance = attendanceRepository
                .findByStudentId(studentId);

        log.info("findAttendance {}", findAttendance);

        if(findAttendance.isEmpty()){
            return Optional.empty();
        }

        var attendances = Attendances.builder()
                .courseId(findAttendance.get().getCourseId())
                .classDate(findAttendance.get().getClassDate())
                .attendanceStatus(findAttendance.get().isAttendanceStatus())
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
        //set courseName
        // buscar dados do student por id
        // setar fullName student
    }
}
