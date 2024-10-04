package com.space.server.ai.service.dto.response.gpt;

import com.space.server.ai.service.dto.request.gpt.Tools;

public record AiAssistantsResponse(
    String id,
    String object,
    Long created_at,
    String name,
    String dscription,
    String model,
    String instructions,
    Tools tools,
    String metadata,
    Double top_p,
    Double temperature,
    String response_format
) {
}
