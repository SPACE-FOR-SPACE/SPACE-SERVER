package com.space.server.domain.inventory.application.port.out;

import com.space.server.domain.inventory.domain.Inventory;

import java.util.List;

public interface LoadInventoriesPort {

  List<Inventory> loadInventories(Long userid);
}
