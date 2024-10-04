package com.space.server.ai.service.dto.request.gpt;

import java.util.List;

public record AiAssistantsRequest(
    String instructions,
    String name,
    List<Tools> tools,
    String model
){
    public AiAssistantsRequest toEntity(String instructions, String name, List<Tools> tools, String model) {
        return new AiAssistantsRequest(instructions, name, tools, model);
    }
}
