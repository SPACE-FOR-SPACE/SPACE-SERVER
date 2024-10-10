package com.space.server.chat.presentation.dto.request;

import com.space.server.chat.domain.value.Type;

public record CreateChatRequest(
        String userChat,
        Type type
) {}
