package com.space.server.ai.service.dto.response.gpt;

import java.util.List;

public record ToolResources(
    List<FileIds> code_interpreter
) {
}
