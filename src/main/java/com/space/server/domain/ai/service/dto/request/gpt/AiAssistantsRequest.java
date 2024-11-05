package com.space.server.domain.ai.service.dto.request.gpt;

import java.util.List;
import java.util.Map;

public record AiAssistantsRequest(
    String instructions,
    String name,
    List<Map<String, String>> tools,
    String model
) {
    public AiAssistantsRequest toEntity(String instructions, String name, List<Map<String, String>> tools, String model) {
        return new AiAssistantsRequest(instructions, name, tools, model);
    }
}
