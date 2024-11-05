package com.space.server.domin.inventory.service.implementation;

import com.space.server.domin.inventory.domain.Inventory;
import com.space.server.domin.inventory.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryCreator {

  private final InventoryRepository inventoryRepository;

  public void create(Inventory inventory) {
    inventoryRepository.save(inventory);
  }
}
