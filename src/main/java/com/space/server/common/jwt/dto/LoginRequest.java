package com.space.server.common.jwt.dto;

public record LoginRequest(
        String email,
        String password
) {}
