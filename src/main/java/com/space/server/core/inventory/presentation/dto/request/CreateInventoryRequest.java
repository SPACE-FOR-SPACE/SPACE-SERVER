package com.space.server.core.inventory.presentation.dto.request;

import com.space.server.core.inventory.domain.Inventory;

public record CreateInventoryRequest (
//    User user
    ) {
  public Inventory toEntity() {
    return new Inventory(/*user*/);
  }
}
