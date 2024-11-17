package com.space.server.domain.item.adapter.out.persistence;

import com.space.server.domain.common.PersistenceAdapter;
import com.space.server.domain.inventory.adapter.out.persistence.InventoryJpaEntity;
import com.space.server.domain.inventory.adapter.out.persistence.InventoryMapper;
import com.space.server.domain.inventory.adapter.out.persistence.InventoryRepository;
import com.space.server.domain.inventory.application.port.out.CreateInventoryPort;
import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.item.application.port.out.BuyItemPort;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.user.domain.Users;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class BuyItemAdapter implements BuyItemPort {

  private final InventoryRepository inventoryRepository;
  private final InventoryMapper inventoryMapper;
  private final CreateInventoryPort createInventoryPort;

  @Override
  public void buyItemPort(Item item, Users user) {
    Inventory inventory = createInventoryPort.create(item, user);
    InventoryJpaEntity itemJpaEntity = inventoryMapper.mapToInventoryJpaEntity(inventory);
    inventoryRepository.save(itemJpaEntity);
  }
}
