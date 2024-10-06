package com.space.server.ai.service.dto.response.gpt;

import java.util.List;
import java.util.Map;

public record ToolResources(
    Map<String, List> code_interpreter,
    Map<String, List> file_search
) {
}
