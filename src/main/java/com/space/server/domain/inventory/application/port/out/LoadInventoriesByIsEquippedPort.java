package com.space.server.domain.inventory.application.port.out;

import com.space.server.domain.inventory.domain.Inventory;

import java.util.List;

public interface LoadInventoriesByIsEquippedPort {

  List<Inventory> loadInventoriesByIsEquippedPort(Long userId);
}
