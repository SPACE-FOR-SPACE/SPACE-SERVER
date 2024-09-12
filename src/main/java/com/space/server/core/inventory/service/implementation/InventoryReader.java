package com.space.server.core.inventory.service.implementation;

import com.space.server.common.exception.ErrorCode;
import com.space.server.common.exception.SpaceException;
import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.domain.repository.InventoryRepository;
import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.space.server.core.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryReader {

  private final InventoryRepository inventoryRepository;

  public Inventory findById(Long inventoryId) {
    return inventoryRepository.findById(inventoryId)
        .orElseThrow(() -> new SpaceException(ErrorCode.INVENTORY_NOT_FOUND));
  }

  public List<Inventory> findByUser(User user) {
    return inventoryRepository.findByUser(user);
  }

  public Inventory findByItemAndUser(Item item, User user) {
    return inventoryRepository.findByItemAndUser(item, user);
  }

  public List<Inventory> findByIsEquippedAndUser(User user) {
    return inventoryRepository.findByIsEquippedAndUser(user);
  }

  public Inventory findByCategoryAndUserAndIsEquipped(Category category, User user) {
    return inventoryRepository.findByCategoryAndUserAndIsEquipped(category, user);
  }
}
