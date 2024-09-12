package com.space.server.core.inventory.presentation.dto.response;

import com.space.server.core.inventory.domain.Inventory;

public record InventoryResponse(
    Long id,
    Long itemId,
    boolean isEquipped
) {
  public static InventoryResponse from(Inventory inventory) {
    return new InventoryResponse(
        inventory.getId(),
        inventory.getItem().getId(),
        inventory.isEquipped()
    );
  }
}
