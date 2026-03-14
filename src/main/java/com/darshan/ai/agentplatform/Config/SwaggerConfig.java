package com.darshan.ai.agentplatform.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI mySwaggerConfig() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("AI-Agent Platform APIs")
                                .description("by Darshan")
                                .version("1.0")
                )
                .servers(
                        List.of(
                                new Server()
                                        .url("http://localhost:8080")
                                        .description("Local Server")
                        )
                );
    }
}
