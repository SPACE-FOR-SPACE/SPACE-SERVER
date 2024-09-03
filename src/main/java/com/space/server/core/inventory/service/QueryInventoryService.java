package com.space.server.core.inventory.service;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.InventoryReader;
import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.value.Category;
import com.space.server.core.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryInventoryService {

  private final InventoryReader inventoryReader;

  public Inventory readOne(Long inventoryId) {
    return inventoryReader.read(inventoryId);
  }

  public List<Inventory> findByUser(User user) {
    return inventoryReader.findByUser(user);
  }

  public boolean findByUserAndItem(User user, Item item) {
    return inventoryReader.findByUserAndItem(user, item);
  }

  public Inventory findByUserAndCategoryAndIsEquipped(User user, Category category) {
    return inventoryReader.findByUserAndCategoryAndIsEquipped(user, category);
  }
}
