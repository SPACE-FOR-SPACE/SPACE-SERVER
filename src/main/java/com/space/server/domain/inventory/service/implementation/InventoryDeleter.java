package com.space.server.domain.inventory.service.implementation;

import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.inventory.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryDeleter {

  private final InventoryRepository inventoryRepository;

  public void delete(Inventory inventory) {
    inventoryRepository.delete(inventory);
  }
}
