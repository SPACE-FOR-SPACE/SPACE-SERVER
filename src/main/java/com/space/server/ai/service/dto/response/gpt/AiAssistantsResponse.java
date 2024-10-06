package com.space.server.ai.service.dto.response.gpt;

import java.util.List;
import java.util.Map;

public record AiAssistantsResponse(
    String id,
    String object,
    Long created_at,
    String name,
    String description,
    String model,
    String instructions,
    List<Map<String, String>> tools,
    ToolResources tool_resources,
    Map<String, String> metadata,
    Double top_p,
    Double temperature,
    String response_format
) {
}
