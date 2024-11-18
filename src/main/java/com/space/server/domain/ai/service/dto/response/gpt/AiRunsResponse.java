package com.space.server.domain.ai.service.dto.response.gpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AiRunsResponse(
    String id,
    String status
) {}
