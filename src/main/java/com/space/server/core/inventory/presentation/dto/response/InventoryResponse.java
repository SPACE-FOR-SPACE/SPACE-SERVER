package com.space.server.core.inventory.presentation.dto.response;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.item.domain.Item;

public record InventoryResponse(
    Long id,
    //User user,
    Item head,
    Item theme,
    int point
) {
  public static InventoryResponse from(Inventory equip) {
    return new InventoryResponse(equip.getId(), equip.getHead(), equip.getTheme(), equip.getPoint());
  }
}
