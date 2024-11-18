package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.inventory.application.port.out.EquipInventoryPort;
import com.space.server.domain.inventory.domain.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@PersistenceAdapter
@RequiredArgsConstructor
public class EquipInventoryAdapter implements EquipInventoryPort {

  private final InventoryRepository inventoryRepository;
  private final InventoryMapper inventoryMapper;

  @Override
  public void equipInventory(Inventory inventory) {
    inventory.equip();
    InventoryJpaEntity inventoryJpaEntity = inventoryMapper.mapToInventoryJpaEntity(inventory);
    inventoryRepository.save(inventoryJpaEntity);
  }

  @Override
  public void unEquipInventory(Inventory inventory) {
    inventory.unEquip();
    InventoryJpaEntity inventoryJpaEntity = inventoryMapper.mapToInventoryJpaEntity(inventory);
    inventoryRepository.save(inventoryJpaEntity);
  }
}
