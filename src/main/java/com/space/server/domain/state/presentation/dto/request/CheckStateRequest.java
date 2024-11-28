package com.space.server.domain.state.presentation.dto.request;

public record CheckStateRequest(
    Long userId,
    Long chapterId
) {
}
