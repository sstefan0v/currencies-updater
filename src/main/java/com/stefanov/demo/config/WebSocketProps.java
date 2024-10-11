package com.stefanov.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "web-socket")
public class WebSocketProps {

    private int textMessageSizeLimit;
}
