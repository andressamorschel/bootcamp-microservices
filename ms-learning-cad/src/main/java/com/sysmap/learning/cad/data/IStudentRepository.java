package com.sysmap.learning.cad.data;

import com.sysmap.learning.cad.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findByStudentId(String studentId);
}
