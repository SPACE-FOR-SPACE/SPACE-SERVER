package com.space.server.domain.ai.service.dto.response.gpt.tools;

public record FileSearchTool(
    String type,
    FileSearch file_search
) {}
