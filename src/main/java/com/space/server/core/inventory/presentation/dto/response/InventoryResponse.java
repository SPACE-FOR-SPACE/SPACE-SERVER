package com.space.server.core.inventory.presentation.dto.response;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.item.domain.value.Category;
import lombok.Builder;

@Builder
public record InventoryResponse(
    Long id,
    Long itemId,
    Category category,
    boolean isEquipped
) {
  public static InventoryResponse from(Inventory inventory) {
    return InventoryResponse.builder()
        .id(inventory.getId())
        .itemId(inventory.getItem().getId())
        .category(inventory.getItem().getCategory())
        .isEquipped(inventory.isEquipped())
        .build();
  }
}
