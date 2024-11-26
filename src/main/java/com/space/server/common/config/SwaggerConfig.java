package com.space.server.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Value("${spring.profiles.active}")
    private String activeProfile;


    @Bean
    public OpenAPI customOpenAPI() {

        OpenAPI openAPI = new OpenAPI();

        openAPI.info(new Info()
                .title("SPACE")
                .description("이 API는 자체 로그인과 소셜 로그인(구글, 네이버, 카카오)을 지원합니다. "
                        + "엑세스 토큰과 리프레시 토큰은 HttpOnly 쿠키로 반환됩니다.\n\n"
                        + "### 소셜 로그인 API 엔드포인트:\n"
                        + "- 카카오 로그인: `/oauth2/authorization/kakao`\n"
                        + "- 네이버 로그인: `/oauth2/authorization/naver`\n"
                        + "- 구글 로그인: `/oauth2/authorization/google`\n\n"
                        + "### 상태 코드 및 오류 메시지 형식:\n"
                        + "| 상태 코드 | 오류 코드 | 설명 |\n"
                        + "| --- | --- | --- |\n"
                        + "| 400 | BAD_WORD_DETECTED | 부적절한 단어는 입력할 수 없습니다. |\n"
                        + "| 400 | ENGLISH_DETECTED | 영어는 입력할 수 없습니다. |\n"
                        + "| 400 | EMAIL_NOT_VERIFIED | 이메일 인증이 필요합니다. |\n"
                        + "| 400 | INVALID_ARGUMENT | 잘못된 값이 들어왔습니다. |\n"
                        + "| 400 | TYPE_MISMATCH | 파라미터 타입이 일치하지 않습니다. |\n"
                        + "| 400 | NULL_VALUE | 필수 값이 누락되었습니다. |\n"
                        + "| 401 | DUPLICATE_LOGIN | 이미 로그인한 상태입니다. |\n"
                        + "| 401 | EXPIRED_REFRESH_TOKEN | 재로그인 해야 합니다. |\n"
                        + "| 401 | EXPIRED_TOKEN | 만료된 토큰입니다. |\n"
                        + "| 401 | INVALID_TOKEN | 잘못된 토큰입니다. |\n"
                        + "| 401 | REFRESH_TOKEN_NOT_FOUND | 리프레시 토큰이 존재하지 않습니다. |\n"
                        + "| 401 | UNAUTHENTICATED_ACCESS | 인증이 필요합니다. |\n"
                        + "| 401 | SECURITY_UNKNOWN | 시큐리티에서 알 수 없는 에러가 발생했습니다. |\n"
                        + "| 401 | INVALID_CREDENTIALS | 잘못된 이메일 또는 비밀번호입니다. |\n"
                        + "| 403 | ACCESS_DENIED | 권한이 필요합니다. |\n"
                        + "| 404 | CHAPTER_NOT_FOUND | 챕터를 찾을 수 없습니다. |\n"
                        + "| 404 | CHECKLIST_NOT_FOUND | 체크리스트를 찾을 수 없습니다. |\n"
                        + "| 404 | INVENTORY_NOT_FOUND | 인벤토리를 찾을 수 없습니다. |\n"
                        + "| 404 | ITEM_NOT_FOUND | 아이템을 찾을 수 없습니다. |\n"
                        + "| 404 | QUIZ_NOT_FOUND | 문제를 찾을 수 없습니다. |\n"
                        + "| 404 | STATE_NOT_FOUND | 상태를 찾을 수 없습니다. |\n"
                        + "| 404 | USER_NOT_FOUND | 유저를 찾을 수 없습니다. |\n"
                        + "| 409 | INVENTORY_ITEM_EXISTED | 아이템을 이미 가지고 있습니다. |\n"
                        + "| 409 | INSUFFICIENT_POINTS | 포인트가 부족합니다. |\n"
                        + "| 409 | USER_EXISTED | 유저가 이미 존재합니다. |\n"
                        + "| 500 | INVALID_CHAT_JSON | JSON이 올바르지 않습니다. |\n"
                        + "| 500 | INVALID_MOVE | move가 올바르지 않습니다. |\n"
                        + "| 500 | SERVER_UNKNOWN | 서버에서 알 수 없는 에러가 발생했습니다. |\n"
                        + "### 오류 메시지 예시:\n"
                        + "```json\n"
                        + "{\n"
                        + "  \"status\": 401,\n"
                        + "  \"errorCode\": \"UNAUTHENTICATED_ACCESS\",\n"
                        + "  \"message\": \"인증이 필요합니다.\",\n"
                        + "  \"timestamp\": \"2024-09-29T10:00:00\"\n"
                        + "}\n"
                        + "```"))

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

        if ("dev".equals(activeProfile)) {
            openAPI.addServersItem(new Server()
                    .url("/")
                    .description("Local Server"));
        } else {
            openAPI.addServersItem(new Server()
                    .url("/api")
                    .description("Production Server"));
        }

        return openAPI;
    }
}
