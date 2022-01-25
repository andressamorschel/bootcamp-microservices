package com.sysmap.attendance.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.attendance.dto.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class MsLearningCourseClient {

    @Value("${client.ms-learning-course.url}")
    private String baseUrl;
    private final ObjectMapper objectMapper;

    public boolean courseExist(String courseId) {
        var uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/v1/courses")
                .queryParam("courseId", courseId)
                .build()
                .toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        try {
            var response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());
            log.info("response: {}", response);
            if (response.statusCode() == 200) {
                return true;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Optional<StudentResponse> getStudent(String studentId) {
        var uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/v1/students/" + studentId)
                .build()
                .toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());
            log.info("response: {}", response);

            var student = objectMapper.readValue(response.body(), StudentResponse.class);

            return Optional.of(student);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


}
