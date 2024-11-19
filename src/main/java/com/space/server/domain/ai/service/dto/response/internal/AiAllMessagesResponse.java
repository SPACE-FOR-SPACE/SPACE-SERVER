package com.space.server.domain.ai.service.dto.response.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AiAllMessagesResponse(
    String first_id
) {}
