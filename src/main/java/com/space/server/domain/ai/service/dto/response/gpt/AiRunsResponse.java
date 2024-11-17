package com.space.server.domain.ai.service.dto.response.gpt;

import com.space.server.domain.ai.service.dto.response.gpt.tools.FileSearchTool;

import java.util.List;
import java.util.Map;

public record AiRunsResponse(
    String id,
    String object,
    Long created_at,
    String assistant_id,
    String thread_id,
    String status,
    Map<String, Map<String, List<String>>> required_action,
    Long started_at,
    Long expires_at,
    Long cancelled_at,
    Long failed_at,
    Long completed_at,
    Map<String, String> last_error,
    String model,
    String instructions,
    List<String> incomplete_details,
    List<FileSearchTool> tools,
    ToolResources tool_resources,
    Map<String, String> metadata,
    Map<Object, Object> usage,
    Double temperature,
    Double top_p,
    Long max_prompt_tokens,
    Long max_completion_tokens,
    TruncationStrategy truncation_strategy,
    String response_format,
    String tool_choice,
    Boolean parallel_tool_calls
) {}
