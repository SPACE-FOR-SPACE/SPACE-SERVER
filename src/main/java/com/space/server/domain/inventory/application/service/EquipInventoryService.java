package com.space.server.domain.inventory.application.service;

import com.space.server.common.annotation.UseCase;
import com.space.server.domain.inventory.application.port.in.EquipInventoryUseCase;
import com.space.server.domain.inventory.application.port.out.EquipInventoryPort;
import com.space.server.domain.inventory.application.port.out.LoadEquippedInventoryByCategoryPort;
import com.space.server.domain.inventory.application.port.out.LoadInventoryPort;
import com.space.server.domain.inventory.domain.Inventory;
import com.space.server.domain.inventory.exception.InventoryNotFoundException;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EquipInventoryService implements EquipInventoryUseCase {

  private final LoadEquippedInventoryByCategoryPort loadEquippedInventoryByCategoryPort;
  private final LoadInventoryPort loadInventoryPort;
  private final EquipInventoryPort equipInventoryPort;

  @Override
  public void equipInventory(Long inventoryId, Long userId) {
    Inventory inventory = loadInventoryPort.loadInventory(inventoryId);

    if(!inventory.getUser().getId().equals(userId)) throw new InventoryNotFoundException();

    Inventory equippedInventory = loadEquippedInventoryByCategoryPort.findEquippedInventoryByCategoryAndUser(
        inventory.getItem().getCategory(),
        userId
    );

    equipInventoryPort.unEquipInventory(equippedInventory);
    equipInventoryPort.equipInventory(inventory);
  }
}
