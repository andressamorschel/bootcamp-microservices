package com.sysmap.learning.cad.service;

import com.sysmap.learning.cad.client.MsLearningCourseClient;
import com.sysmap.learning.cad.dto.request.StudentRequest;
import com.sysmap.learning.cad.data.StudentRepository;
import com.sysmap.learning.cad.domain.Student;
import com.sysmap.learning.cad.exception.CourseIdNotFound;
import com.sysmap.learning.cad.exception.StudentNotFound;
import com.sysmap.learning.cad.mapper.StudentMapper;
import com.sysmap.learning.cad.dto.response.FindStudentResponse;
import com.sysmap.learning.cad.dto.response.CourseResponse;
import com.sysmap.learning.cad.dto.response.StudentCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final MsLearningCourseClient msLearningCourseClient;

    public StudentCreateResponse createStudent(StudentRequest studentRequest) {

        var student = studentMapper.studentRequestToEntity(studentRequest);
        var courses = msLearningCourseClient.getCourseById(student.getCourseId());

        log.info("inferno {}", courses);
        if(courses.isEmpty()){
            throw new CourseIdNotFound(student.getCourseId());
        }

        var completedStudent = completeDefaultStudentInfo(student);
        var response = studentRepository.save(completedStudent);

        return studentMapper.entityToStudentResponse(response);
    }

    private Student completeDefaultStudentInfo(Student student) {
        return student.toBuilder()
                .studentId(UUID.randomUUID().toString())
                .status(true)
                .createdOn(LocalDateTime.now())
                .build();
    }

    public FindStudentResponse findStudentById(String studentId) {

        var student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new StudentNotFound("student not found"));

        var course = msLearningCourseClient.getCourseById(student.getCourseId());
        var courseName = course.stream()
                .map(CourseResponse::getCourseName)
                .toList();

        return FindStudentResponse.builder()
                .fullName(student.getFirstName() + " " + student.getLastName())
                .courseName(courseName)
                .status(student.isStatus())
                .document(student.getDocument())
                .birthDate(student.getBirthDate())
                .build();
    }

}
