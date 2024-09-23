package com.space.server.core.inventory.service;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.InventoryReader;
import com.space.server.core.user.Users;
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
    return inventoryReader.findById(inventoryId);
  }

  public List<Inventory> readMine(Users user) {
    return inventoryReader.findByUser(user);
  }

  public List<Inventory> readIsEquipped(Users user) {
    return inventoryReader.findByIsEquippedAndUser(user);
  }
}
