package com.example.qrgeneratorexample.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("QR Generator API")
                        .version("1.0")
                        .description("""
                                This is an api provides us to
                                generate qr code with image or without image
                                """
                        ));
    }
}
