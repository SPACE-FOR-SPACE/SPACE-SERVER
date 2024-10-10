package com.space.server.chat.presentation.dto.response;

import com.space.server.chat.domain.value.Type;

public record ChatResponse(
    Integer request_order,
    String botChat,
    String userChat
    ) {
}
