package com.space.server.core.inventory.service.implementation;

import com.space.server.core.inventory.domain.repository.InventoryRepository;
import com.space.server.core.inventory.exception.InventoryItemFoundException;
import com.space.server.core.item.domain.Item;
import com.space.server.user.domain.Users;
import com.space.server.user.exception.InsufficientPointsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryValidator {
  private final InventoryRepository inventoryRepository;

  public void hasItem(Item item, Users user) {
    if(inventoryRepository.existsByItemAndUser(item, user)) {
      throw new InventoryItemFoundException();
    }
  }
  
  public void buyItem(Item item, Users user) {
    if(item.getPrice() > user.getPoint()) {
      throw new InsufficientPointsException();
    }
    else user.payPoint(item.getPrice());
  }
}
