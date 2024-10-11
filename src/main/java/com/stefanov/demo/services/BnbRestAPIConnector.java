package com.stefanov.demo.services;

import com.stefanov.demo.config.BnbUrlProps;
import com.stefanov.demo.services.enums.LanguageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
@Slf4j
public class BnbRestAPIConnector {

    private final BnbUrlProps bnbUrlProps;
    private final WebClient webClient;

    @Autowired
    public BnbRestAPIConnector(BnbUrlProps bnbUrlProps, WebClient.Builder webBuilder) {
        this.bnbUrlProps = bnbUrlProps;
        this.webClient = webBuilder.baseUrl(bnbUrlProps.getBase()).build();
    }

    public String fetchXmlData(LanguageCode languageCode) {
        return webClient.get()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                            .path(bnbUrlProps.getCurrencies())
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
