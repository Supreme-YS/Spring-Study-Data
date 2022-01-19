package com.supreme.oembed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class OembedApplication {

	public static void main(String[] args) {
		SpringApplication.run(OembedApplication.class, args);
	}

}
