package com.sysmap.learning.cad.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysmap.learning.cad.exception.HttpException;
import com.sysmap.learning.cad.dto.response.CourseResponse;
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
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class MsLearningCourseClient {

    @Value("${client.ms-learning-course.url}")
    private String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public List<CourseResponse> getCourseById(String courseId) {
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

        try {
            var response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());
            log.info("response client: {}", response);
            if (response.statusCode() == 404) {
                return Collections.emptyList();
            }
            return objectMapper.readValue(
                    response.body(), new TypeReference<>() {
                    });
        } catch (IOException | InterruptedException e) {
            throw new HttpException("error to call http service", e.getCause());
        }
    }

}
