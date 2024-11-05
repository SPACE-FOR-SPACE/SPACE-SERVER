package com.space.server.domin.inventory.service.implementation;

import com.space.server.domin.inventory.domain.Inventory;
import com.space.server.domin.inventory.domain.repository.InventoryRepository;
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
