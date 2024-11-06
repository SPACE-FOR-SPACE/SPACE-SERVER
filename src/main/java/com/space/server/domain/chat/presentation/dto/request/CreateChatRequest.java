package com.space.server.domain.chat.presentation.dto.request;

import com.space.server.domain.chat.domain.value.Type;

public record CreateChatRequest(
        String userChat,
        Type type
) {}
