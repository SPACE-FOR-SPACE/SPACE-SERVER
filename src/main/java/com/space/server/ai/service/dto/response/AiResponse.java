package com.space.server.ai.service.dto.response;

import java.util.Map;

public record AiResponse(
    String id,
    String object,
    Integer created,
    String model,
    AiChoices choices,
    AiUsage usage,
    String system_fingerprint,
    Map<String, String> x_groq
) {
}
