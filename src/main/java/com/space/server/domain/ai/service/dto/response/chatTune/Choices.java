package com.space.server.domain.ai.service.dto.response.chatTune;

public record Choices(
    String finish_reason,
    Integer index,
    Message message,
    Object logprobs
) {
}
