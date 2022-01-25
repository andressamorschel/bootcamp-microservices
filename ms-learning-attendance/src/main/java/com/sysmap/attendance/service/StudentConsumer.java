package com.sysmap.attendance.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.attendance.domain.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentConsumer {

    private static final Logger log = LoggerFactory.getLogger(StudentConsumer.class);
    private final ObjectMapper objectMapper;
    private final StudentService studentService;

    @Value("${topic.name.consumer")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(@Payload String studentMessage){

        try {
            var student = objectMapper.readValue(studentMessage, Student.class);
            studentService.createStudent(student);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("Tópico: {}", topicName);
        log.info("Mensagem recebida: {}", studentMessage);
    }

}
