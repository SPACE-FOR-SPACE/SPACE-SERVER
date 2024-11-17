package com.space.server.domain.auth.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record JoinUserRequest(
    @Schema(description = "사용자 이메일", example = "user@example.com", required = true)
    String email,

    @Schema(description = "사용자 이름", example = "user123", required = true)
    String username,

    @Schema(description = "비밀번호", example = "password123", required = true)
    String password,

    @Schema(description = "사용자 나이", example = "13", required = true)
    Integer age
) {}
