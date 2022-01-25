package com.sysmap.attendance.service;

import com.sysmap.attendance.data.StudentRepository;
import com.sysmap.attendance.domain.Student;
import com.sysmap.attendance.exception.StudentNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student){
        studentRepository.save(student);
    }

    public boolean findStudentById(String studentId){
        var studentExist = studentRepository.findById(studentId);
        return studentExist.isPresent();
    }

}
