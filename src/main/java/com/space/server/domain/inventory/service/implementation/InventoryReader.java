package com.space.server.domain.inventory.service.implementation;

import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.inventory.domain.repository.InventoryRepository;
import com.space.server.domain.inventory.exception.InventoryNotFoundException;
import com.space.server.domain.item.domain.value.Category;
import com.space.server.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryReader {

  private final InventoryRepository inventoryRepository;

  public Inventory findById(Long inventoryId) {
    return inventoryRepository.findById(inventoryId)
        .orElseThrow(InventoryNotFoundException::new);
  }

  public List<Inventory> findByUser(Users user) {
    return inventoryRepository.findByUser(user);
  }

  public List<Inventory> findByIsEquippedAndUser(Users user) {
    return inventoryRepository.findByIsEquippedAndUser(user);
  }

  public Inventory findByCategoryAndUserAndIsEquipped(Category category, Users user) {
    return inventoryRepository.findByCategoryAndUserAndIsEquipped(category, user);
  }
}
