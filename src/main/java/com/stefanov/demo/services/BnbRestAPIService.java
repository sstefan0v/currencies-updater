package com.stefanov.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BNBRestAPIService {

    private final Props props;
    private final WebClient webClient;

    public BNBRestAPIService(Props props, WebClient.Builder webBuilder) {
        this.props = props;
        this.webClient = webBuilder.baseUrl(props.getBnbBaseUrl()).build();
    }

    public String fetchXmlData() {
        Mono<String> mono = webClient.get()
                .uri(props.getBnbCurrenciesUrl())
                .retrieve()
                .bodyToMono(String.class);
        return  mono.block();
    }
}
