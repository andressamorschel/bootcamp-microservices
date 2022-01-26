package com.sysmap.learning.cad.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.learning.cad.client.MsLearningCourseClient;
import com.sysmap.learning.cad.data.IStudentRepository;
import com.sysmap.learning.cad.domain.Student;
import com.sysmap.learning.cad.dto.request.CreatedStudentEvent;
import com.sysmap.learning.cad.dto.request.StudentRequest;
import com.sysmap.learning.cad.dto.response.CourseResponse;
import com.sysmap.learning.cad.dto.response.CreateStudentResponse;
import com.sysmap.learning.cad.dto.response.FindStudentResponse;
import com.sysmap.learning.cad.exception.CourseIdNotFound;
import com.sysmap.learning.cad.exception.StudentNotFound;
import com.sysmap.learning.cad.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {

    private final IStudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final MsLearningCourseClient msLearningCourseClient;
    private final ObjectMapper objectMapper;
    private final EventService eventService;

    public CreateStudentResponse createStudent(StudentRequest studentRequest) {

        var student = studentMapper.studentRequestToEntity(studentRequest);
        var courses = msLearningCourseClient.getCourseById(student.getCourseId());

        log.info("courses: {}", courses);
        if (courses.isEmpty()) {
            throw new CourseIdNotFound(student.getCourseId());
        }

        var completedStudent = completeDefaultStudentInfo(student);
        var response = studentRepository.save(completedStudent);

        return studentMapper.entityToStudentResponse(response);
    }

    private Student completeDefaultStudentInfo(Student student) {

        var studentEvent = new CreatedStudentEvent(
                student.getStudentId(),
                student.getFirstName() + " " + student.getLastName(),
                student.getCourseId()
        );

        try {
            String event = objectMapper.writeValueAsString(studentEvent);
            eventService.sendStudent(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return student.toBuilder()
                .studentId(UUID.randomUUID().toString())
                .status(true)
                .birthDate(student.getBirthDate())
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
