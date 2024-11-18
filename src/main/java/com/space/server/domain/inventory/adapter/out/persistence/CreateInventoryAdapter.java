package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.inventory.application.port.out.CreateInventoryPort;
import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.user.domain.Users;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CreateInventoryAdapter implements CreateInventoryPort {

  @Override
  public Inventory create(Item item, Users user) {
    return Inventory.builder()
        .item(item)
        .user(user)
        .isEquipped(false)
        .build();
  }
}
