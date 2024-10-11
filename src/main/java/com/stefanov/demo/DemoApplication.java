package com.stefanov.demo;

import com.stefanov.demo.config.BnbUrlProps;
import com.stefanov.demo.config.WebSocketProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({BnbUrlProps.class, WebSocketProps.class})
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
