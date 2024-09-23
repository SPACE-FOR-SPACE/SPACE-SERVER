package com.space.server.auth.presentation.dto.request;

public record JoinUserRequest(
        String email,
        String username,
        String password,
        Integer age
) {
}
