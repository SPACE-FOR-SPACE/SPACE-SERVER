package com.space.server.ai.service.dto.response.gpt;

public record TruncationStrategy(
    String type,
    String last_messages
) {
}
