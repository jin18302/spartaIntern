package com.sparta.internTask.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server-url}")
    private String swaggerServerUrl;

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("JWT를 사용한 인증 인가 API")
                .version("v1.0.0")
                .description("JWT를 사용한 회원가입, 로그인 API")
                .contact(new Contact()
                        .name("팀 TurtleMart")
                        .email("support@turtlemart.com"));

        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        return new OpenAPI()
                .info(info)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth", bearerAuth))
                .servers(List.of(
                        new Server().url(swaggerServerUrl).description("EC2 서버")
                ));
    }

}
