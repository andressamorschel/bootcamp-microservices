package com.sysmap.attendance.data;

import com.sysmap.attendance.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface IStudentRepository extends CrudRepository<Student, String> {
}
