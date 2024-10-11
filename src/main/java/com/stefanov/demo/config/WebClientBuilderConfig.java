package com.stefanov.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientBuilderConfig {

    @Bean(name = "webClientBuilder")
    public WebClient.Builder wBuilder() {
        return WebClient.builder();
    }
}