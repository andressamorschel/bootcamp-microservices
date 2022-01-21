package com.sysmap.learning.cad.controller;

import com.sysmap.learning.cad.dto.request.StudentRequest;
import com.sysmap.learning.cad.dto.response.FindStudentResponse;
import com.sysmap.learning.cad.service.StudentService;
import com.sysmap.learning.cad.dto.response.StudentCreateResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentCreateResponse createStudent(@RequestBody StudentRequest request) {
        return studentService.createStudent(request);
    }

    @GetMapping("/{studentId}")
    public FindStudentResponse findStudentById(@PathVariable String studentId) {
        return studentService.findStudentById(studentId);
    }

}
