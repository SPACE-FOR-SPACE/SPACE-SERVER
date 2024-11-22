package com.space.server.domain.ai.service.dto.response.chatTune;

import java.util.List;

public record Message(
    String content,
    String refusal,
    List<String> tool_calls,
    String role,
    Object functional_call,
    Object audio
) {
}
