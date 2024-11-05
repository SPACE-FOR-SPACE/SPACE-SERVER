package com.space.server.domin.chat.presentation.dto.request;

import com.space.server.domin.chat.domain.value.Type;

public record CreateChatRequest(
        String userChat,
        Type type
) {}
