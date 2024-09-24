package com.space.server.core.inventory.service;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.InventoryReader;
import com.space.server.user.domain.Users;
import com.space.server.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryInventoryService {

  private final InventoryReader inventoryReader;
  private final UserReader userReader;

  public Inventory readOne(Long inventoryId) {
    return inventoryReader.findById(inventoryId);
  }

  public List<Inventory> readMine(Long userId) {
    Users user = userReader.findById(userId);
    return inventoryReader.findByUser(user);
  }

  public List<Inventory> readIsEquipped(Long userId) {
    Users user = userReader.findById(userId);
    return inventoryReader.findByIsEquippedAndUser(user);
  }
}
