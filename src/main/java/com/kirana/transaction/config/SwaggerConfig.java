package com.kirana.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
	
	@Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Kirana stores Transaction tracking application")
            .description("This application is a backend service designed to empower Kirana stores in managing their transaction registers")
            .version("v0.0.1"));
	}
}
