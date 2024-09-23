package com.space.server.core.inventory.presentation.dto.request;

import com.space.server.core.user.Users;

public record InventoryRequest(
    Users user,
    Long itemId
    ) {}
