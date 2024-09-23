package com.space.server.core.inventory.service.implementation;

import com.space.server.common.exception.ErrorCode;
import com.space.server.common.exception.SpaceException;
import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.domain.repository.InventoryRepository;
import com.space.server.core.item.domain.value.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.space.server.core.user.Users;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryReader {

  private final InventoryRepository inventoryRepository;

  public Inventory findById(Long inventoryId) {
    return inventoryRepository.findById(inventoryId)
        .orElseThrow(() -> new SpaceException(ErrorCode.INVENTORY_NOT_FOUND));
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
