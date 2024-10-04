package com.space.server.ai.service.dto.response.gpt;

import com.space.server.ai.service.dto.request.gpt.Tools;

public record AiRunsResponse(
    String id,
    String object,
    Long created_at,
    String assistant_id,
    String thread_id,
    String status,
    Long started_at,
    Long expires_at,
    Long cancelled_at,
    Long failed_at,
    Long completed_at,
    String last_error,
    String model,
    String instructions,
    String incomplete_details,
    Tools tools,
    String metadata,
    String usage,
    Double temperature,
    Double top_p,
    Long max_prompt_tokens,
    Long max_completion_tokens,
    TruncationStrategy truncation_strategy,
    String response_format,
    String tool_choice,
    Boolean parallel_tool_calls
) {
}
