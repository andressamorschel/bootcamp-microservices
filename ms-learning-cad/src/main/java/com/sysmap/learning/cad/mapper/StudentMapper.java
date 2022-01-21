package com.sysmap.learning.cad.mapper;

import com.sysmap.learning.cad.dto.request.StudentRequest;
import com.sysmap.learning.cad.domain.Student;
import com.sysmap.learning.cad.dto.response.StudentCreateResponse;
import com.sysmap.learning.cad.dto.response.FindStudentResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final ModelMapper modelMapper;

    public StudentCreateResponse entityToStudentResponse(Student student){
        return modelMapper.map(student, StudentCreateResponse.class);
    }

    public Student studentResponseToEntity(StudentCreateResponse studentCreateResponse){
        return modelMapper.map(studentCreateResponse, Student.class);
    }

    public Student studentRequestToEntity(StudentRequest studentCreateRequest){
        return modelMapper.map(studentCreateRequest, Student.class);
    }

    public Student studentFindByIdResponseToEntity(FindStudentResponse studentFindByIdResponse){
        return modelMapper.map(studentFindByIdResponse, Student.class);
    }

}
