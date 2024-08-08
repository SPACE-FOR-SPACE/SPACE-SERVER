package com.space.server.core.inventory.presentation.dto.request;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.item.domain.Item;

public record UpdateInventoryRequest(
    Long headId,
    Long themeId,
    int point
) {
}