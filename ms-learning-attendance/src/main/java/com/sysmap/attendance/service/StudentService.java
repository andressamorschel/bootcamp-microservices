package com.sysmap.attendance.service;

import com.sysmap.attendance.data.IStudentRepository;
import com.sysmap.attendance.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final IStudentRepository studentRepository;

    public void createStudent(Student student){
        studentRepository.save(student);
    }

    public boolean findStudentById(String studentId){
        var studentExist = studentRepository.findById(studentId);
        return studentExist.isPresent();
    }

}
