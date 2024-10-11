package com.stefanov.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "bnb.url")
public class BnbUrlProps {

    private String base;
    private String currencies;
}
