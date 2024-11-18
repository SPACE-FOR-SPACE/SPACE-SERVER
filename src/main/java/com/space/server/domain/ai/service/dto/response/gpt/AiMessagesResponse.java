package com.space.server.domain.ai.service.dto.response.gpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AiMessagesResponse(
    List<MessagesContent> content
) {}
