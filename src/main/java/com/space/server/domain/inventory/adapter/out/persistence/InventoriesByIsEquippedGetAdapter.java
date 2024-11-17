package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.domain.common.PersistenceAdapter;
import com.space.server.domain.inventory.application.port.out.LoadInventoriesByIsEquippedPort;
import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class InventoriesByIsEquippedGetAdapter implements LoadInventoriesByIsEquippedPort {

  private final InventoryRepository inventoryRepository;
  private final InventoryMapper inventoryMapper;
  private final UserRepository userRepository;


  @Override
  public List<Inventory> loadInventoriesByIsEquippedPort(Long userId) {
    return inventoryRepository.findByIsEquippedAndUser(userRepository.findById(userId).get()).stream()
        .map(inventoryMapper::mapToInventory)
        .toList();
  }
}
