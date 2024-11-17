package com.space.server.domain.inventory.application.port.in;

import com.space.server.domain.inventory.domain.Inventory;

import java.util.List;

public interface GetInventoriesByIsEquippedQuery {

  List<Inventory> getInventoriesByIsEquippedQuery(Long userId);
}
