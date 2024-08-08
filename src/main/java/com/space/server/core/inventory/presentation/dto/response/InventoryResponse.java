package com.space.server.core.inventory.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.item.domain.Item;

public record InventoryResponse(
    Long id,
    //User user,
    @JsonIgnore
    Item head,
    @JsonIgnore
    Item theme,
    int point
) {
  public static InventoryResponse from(Inventory inventory) {
    return new InventoryResponse(inventory.getId(), inventory.getHead(), inventory.getTheme(), inventory.getPoint());
  }
}
