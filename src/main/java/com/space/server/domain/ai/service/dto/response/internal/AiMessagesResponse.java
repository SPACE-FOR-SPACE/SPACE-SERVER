package com.space.server.domain.ai.service.dto.response.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AiMessagesResponse(
    List<MessagesContent> content
) {}
