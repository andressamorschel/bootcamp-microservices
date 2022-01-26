package com.sysmap.attendance.data;

import com.sysmap.attendance.domain.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, String> {

    Attendance findByStudentId(String studentId);


}
