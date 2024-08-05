package com.space.server.core.inventory.service;

import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.InventoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryInventoryService {

  private final InventoryReader inventoryReader;

  public Inventory fineOne(Long inventoryId) {
    return inventoryReader.read(inventoryId);
  }
}
