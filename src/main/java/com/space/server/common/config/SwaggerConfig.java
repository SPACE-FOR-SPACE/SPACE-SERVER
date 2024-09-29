package com.space.server.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {



        return new OpenAPI()
                .info(new Info()
                        .title("SPACE")
                        .description("이 API는 자체 로그인과 소셜 로그인(구글, 네이버, 카카오)을 지원합니다. "
                                + "엑세스 토큰과 리프레시 토큰은 HttpOnly 쿠키로 반환됩니다."))  // API 기본 정보를 설정합니다.

                // JWT 보안 요구 사항을 추가합니다.
                .addSecurityItem(new SecurityRequirement().addList("jwtAuth"))

                // Security Scheme 설정을 추가합니다.
                .components(new Components()
                        .addSecuritySchemes("jwtAuth", new SecurityScheme()
                                .name("JWT Authentication")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("엑세스 토큰과 리프레시 토큰은 HttpOnly 쿠키로 설정됩니다. "
                                        + "API 호출 시 클라이언트는 쿠키로 인증이 진행됩니다.")));
    }
}
