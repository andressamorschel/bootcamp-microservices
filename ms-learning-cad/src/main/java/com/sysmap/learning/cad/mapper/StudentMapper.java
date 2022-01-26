package com.sysmap.learning.cad.mapper;

import com.sysmap.learning.cad.dto.request.StudentRequest;
import com.sysmap.learning.cad.domain.Student;
import com.sysmap.learning.cad.dto.response.CreateStudentResponse;
import com.sysmap.learning.cad.dto.response.FindStudentResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final ModelMapper modelMapper;

    public CreateStudentResponse entityToStudentResponse(Student student){
        return modelMapper.map(student, CreateStudentResponse.class);
    }

    public Student studentRequestToEntity(StudentRequest studentCreateRequest){
        return modelMapper.map(studentCreateRequest, Student.class);
    }

}
