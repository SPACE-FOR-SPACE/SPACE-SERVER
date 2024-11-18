package com.space.server.domain.inventory.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.inventory.application.port.out.EquipInventoryPort;
import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.item.domain.value.Category;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.domain.repository.UserRepository;
import com.space.server.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@PersistenceAdapter
@RequiredArgsConstructor
public class EquipInventoryAdapter implements EquipInventoryPort {

  private final InventoryRepository inventoryRepository;
  private final InventoryMapper inventoryMapper;
  private final UserRepository userRepository;

  @Override
  public Inventory findEquippedInventoryByCategoryAndUser(Category category, Long userId) {
    Users user = userRepository.findById(userId)
        .orElseThrow(UserNotFoundException::new);

    InventoryJpaEntity equippedByCategoryInventory = inventoryRepository.findByCategoryAndUserAndIsEquipped(category, user);
    return inventoryMapper.mapToInventory(equippedByCategoryInventory);
  }
  @Override
  public void equipInventory(Inventory inventory) {
    inventory.equip();
    InventoryJpaEntity inventoryJpaEntity = inventoryMapper.mapToInventoryJpaEntity(inventory);
    inventoryRepository.save(inventoryJpaEntity);
  }

  @Override
  public void unEquipInventory(Inventory inventory) {
    inventory.unEquip();
    InventoryJpaEntity inventoryJpaEntity = inventoryMapper.mapToInventoryJpaEntity(inventory);
    inventoryRepository.save(inventoryJpaEntity);
  }
}
