package com.space.server.core.inventory.presentation.dto.request;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.item.domain.Item;

public record UpdateInventoryRequest(
    Item head,
    Item theme,
    int point
) {
  public Inventory toEntity() {
    return new Inventory(head, theme, point);
  }
}