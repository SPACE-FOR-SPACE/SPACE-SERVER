package com.space.server.core.inventory.presentation.dto.request;

import com.space.server.core.user.User;

public record CreateInventoryRequest (
    User user,
    Long itemId
    ) {}
