package com.space.server.domain.ai.service.dto.response.gpt.tools;

import java.util.Map;

public record FileSearch(
    Long max_num_results,
    Map<String, String> ranking_options
) {}
