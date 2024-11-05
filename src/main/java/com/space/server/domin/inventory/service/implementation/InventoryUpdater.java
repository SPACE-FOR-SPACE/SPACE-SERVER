package com.space.server.domin.inventory.service.implementation;

import com.space.server.domin.inventory.domain.Inventory;
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
