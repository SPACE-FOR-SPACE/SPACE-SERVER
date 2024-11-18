package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.inventory.application.port.out.LoadEquippedInventoryByCategoryPort;
import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.item.domain.value.Category;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.domain.repository.UserRepository;
import com.space.server.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class EquippedInventoryByCategoryGetAdapter implements LoadEquippedInventoryByCategoryPort {

  private final InventoryRepository inventoryRepository;
  private final UserRepository userRepository;
  private final InventoryMapper inventoryMapper;

  @Override
  public Inventory findEquippedInventoryByCategoryAndUser(Category category, Long userId) {
    Users user = userRepository.findById(userId)
        .orElseThrow(UserNotFoundException::new);

    InventoryJpaEntity equippedByCategoryInventory = inventoryRepository.findByCategoryAndUserAndIsEquipped(category, user);
    return inventoryMapper.mapToInventory(equippedByCategoryInventory);
  }
}
