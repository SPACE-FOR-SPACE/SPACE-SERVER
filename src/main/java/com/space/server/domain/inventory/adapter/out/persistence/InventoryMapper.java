package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.item.adapter.out.persistence.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryMapper {

  private final ItemMapper itemMapper;

  public Inventory mapToInventory(
      InventoryJpaEntity inventory
  ) {
    return Inventory.builder()
        .id(new Inventory.InventoryId(inventory.getId()))
        .user(inventory.getUser())
        .item(itemMapper.mapToItem(inventory.getItem()))
        .isEquipped(inventory.isEquipped())
        .build();
  }

  public InventoryJpaEntity mapToInventoryJpaEntity(Inventory inventory) {
    return InventoryJpaEntity.builder()
        .id(inventory.getId() == null ? null : inventory.getId().getValue())
        .user(inventory.getUser())
        .item(itemMapper.mapToItemJpaEntity(inventory.getItem()))
        .isEquipped(inventory.isEquipped())
        .build();
  }
}
