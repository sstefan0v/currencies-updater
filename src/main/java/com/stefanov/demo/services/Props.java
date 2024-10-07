package com.stefanov.demo.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@Getter
public class Props {

    @Value("${bnb.url.base}")
    private String bnbBaseUrl;

    @Value("${bnb.url.currencies}")
    private String bnbCurrenciesUrl;


}
