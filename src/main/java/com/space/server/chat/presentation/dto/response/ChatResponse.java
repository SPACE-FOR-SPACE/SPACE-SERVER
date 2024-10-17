package com.space.server.chat.presentation.dto.response;

public record ChatResponse(
    Integer request_order,
    String botChat,
    String userChat
) {}
