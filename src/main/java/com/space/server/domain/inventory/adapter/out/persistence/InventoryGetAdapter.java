package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.inventory.application.port.out.LoadInventoryPort;
import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.inventory.exception.InventoryNotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class InventoryGetAdapter implements LoadInventoryPort {

  private final InventoryRepository inventoryRepository;
  private final InventoryMapper inventoryMapper;

  @Override
  public Inventory loadInventory(Long inventoryId) {
    return inventoryMapper.mapToInventory(inventoryRepository.findById(inventoryId)
        .orElseThrow(InventoryNotFoundException::new));
  }
}
