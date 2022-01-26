package com.sysmap.learning.cad.controller;

import com.sysmap.learning.cad.dto.request.StudentRequest;
import com.sysmap.learning.cad.dto.response.FindStudentResponse;
import com.sysmap.learning.cad.dto.response.CreateStudentResponse;
import com.sysmap.learning.cad.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateStudentResponse createStudent(@RequestBody @Valid StudentRequest request) {
        return studentService.createStudent(request);
    }

    @GetMapping("/{studentId}")
    public FindStudentResponse findStudentById(@PathVariable String studentId) {
        return studentService.findStudentById(studentId);
    }

}
