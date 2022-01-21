package com.sysmap.learning.cad.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class HttpClientConfig {

    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newBuilder().build();
    }
}
