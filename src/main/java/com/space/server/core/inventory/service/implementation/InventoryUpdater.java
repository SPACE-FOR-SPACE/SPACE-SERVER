package com.space.server.core.inventory.service.implementation;

import com.space.server.core.inventory.domain.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryUpdater {

  public void equip(Inventory inventory) {
    inventory.equip();
  }

  public void unEquip(Inventory inventory) {
    inventory.unEquip();
  }
}
