package com.space.server.core.inventory.service.implementation;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.domain.repository.InventoryRepository;
import com.space.server.core.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.space.server.core.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryReader {

  private final InventoryRepository inventoryRepository;

  public Inventory read(Long inventoryId) {
    return inventoryRepository.getById(inventoryId);
  }

  public List<Inventory> findByUser(User user) {
    return inventoryRepository.findByUser(user);
  }

  public boolean findByUserAndInventory(User user, Item item) {
    return inventoryRepository.findByUserAndItem(user, item);
  }
}
