package com.space.server.domain.ai.service.dto.response;


public record AiResponse (
    Boolean isSuccess,
    Long[] score,
    String feedback,
    String[] move
) {}
