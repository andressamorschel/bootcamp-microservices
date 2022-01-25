package com.sysmap.attendance.data;

import com.sysmap.attendance.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {
}
