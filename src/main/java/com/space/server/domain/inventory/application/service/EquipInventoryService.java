package com.space.server.domain.inventory.application.service;

import com.space.server.domain.common.UseCase;
import com.space.server.domain.inventory.application.port.in.EquipInventoryUseCase;
import com.space.server.domain.inventory.application.port.out.EquipInventoryPort;
import com.space.server.domain.inventory.application.port.out.LoadInventoryPort;
import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.inventory.exception.InventoryNotFoundException;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EquipInventoryService implements EquipInventoryUseCase {

  private final EquipInventoryPort equipInventoryPort;
  private final LoadInventoryPort loadInventoryPort;

  @Override
  public void equipInventory(Long userId, Long inventoryId) {
    Inventory inventory = loadInventoryPort.loadInventory(inventoryId);

    if(!inventory.getUser().getId().equals(userId)) throw new InventoryNotFoundException();

    Inventory equippedInventory = equipInventoryPort.findEquippedInventoryByCategoryAndUser(
        inventory.getItem().getCategory(),
        userId
    );

    equipInventoryPort.unEquipInventory(equippedInventory);
    equipInventoryPort.equipInventory(inventory);
  }
}
