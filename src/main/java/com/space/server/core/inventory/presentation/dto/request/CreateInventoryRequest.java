package com.space.server.core.inventory.presentation.dto.request;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.item.domain.Item;

public record CreateInventoryRequest(
    Long itemId
) {
  public Inventory toEntity(Item item) {
    return new Inventory(item);
  }
}
