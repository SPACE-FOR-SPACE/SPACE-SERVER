package com.space.server.domain.ai.service.dto.response.chatTune;

import java.util.List;
import java.util.Optional;

public record Message(
    Optional<String> content,
    Optional<String> refusal,
    List<String> tool_calls,
    String role,
    Object functional_call,
    Optional<Object> audio
) {
}
