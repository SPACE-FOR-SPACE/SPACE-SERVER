package com.space.server.core.inventory.presentation.dto.response;

import com.space.server.core.inventory.domain.Inventory;
import lombok.Builder;

@Builder
public record InventoryResponse(
    Long id,
    Long itemId,
    boolean isEquipped
) {
  public static InventoryResponse from(Inventory inventory) {
    return InventoryResponse.builder()
        .id(inventory.getId())
        .itemId(inventory.getItem().getId())
        .isEquipped(inventory.isEquipped())
        .build();
  }
}
