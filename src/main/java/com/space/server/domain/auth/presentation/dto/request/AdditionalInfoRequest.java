package com.space.server.domain.auth.presentation.dto.request;

public record AdditionalInfoRequest(
        Integer age,
        String username
) {
}
