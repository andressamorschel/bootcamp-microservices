package com.sysmap.attendance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.attendance.data.AttendanceRepository;
import com.sysmap.attendance.domain.Attendance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class TestDaChina {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @PostConstruct
    public void teste() throws JsonProcessingException {
        var attendance = new Attendance();
        attendance.setStudentId("4");
        var objectMapper = new ObjectMapper();
        var result = attendanceRepository.save(attendance);
        log.info("result {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        var result2 = attendanceRepository.findById(result.getId());
        log.info("result2 {}", result2.orElse(null));
        var result3 = attendanceRepository.findByStudentId("4");
        log.info("result3 {}", result3);

    }
}
