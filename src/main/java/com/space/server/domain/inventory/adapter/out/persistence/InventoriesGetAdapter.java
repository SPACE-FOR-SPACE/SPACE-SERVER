package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.inventory.application.port.out.LoadInventoriesPort;
import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.user.domain.repository.UserRepository;
import com.space.server.domain.user.exception.UserExistedException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class InventoriesGetAdapter implements LoadInventoriesPort {

  private final InventoryRepository inventoryRepository;
  private final InventoryMapper inventoryMapper;
  private final UserRepository userRepository;

  @Override
  public List<Inventory> loadInventories(Long userId) {
    return inventoryRepository.findByUser(userRepository.findById(userId)
            .orElseThrow(UserExistedException::new)).stream()
        .map(inventoryMapper::mapToInventory)
        .toList();
  }
}
