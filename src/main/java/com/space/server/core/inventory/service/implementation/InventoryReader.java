package com.space.server.core.inventory.service.implementation;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryReader {

  private final InventoryRepository inventoryRepository;

  public Inventory read(Long inventoryId) {
    return inventoryRepository.getById(inventoryId);
  }
}
