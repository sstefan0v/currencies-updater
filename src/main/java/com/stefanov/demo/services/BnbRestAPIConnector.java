package com.stefanov.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
@Slf4j
public class BnbRestAPIConnector {

    private final Props props;
    private final WebClient webClient;

    public BnbRestAPIConnector(Props props, WebClient.Builder webBuilder) {
        this.props = props;
        this.webClient = webBuilder.baseUrl(props.getBnbBaseUrl()).build();
    }

    public String fetchXmlData(LanguageCode languageCode) {
        return webClient.get()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                            .path(props.getBnbCurrenciesUrl())
                            .queryParam("download", "xml")
                            .queryParam("search", "")
                            .queryParam("lang", languageCode.getLang())
                            .build();
                    return uri;
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
