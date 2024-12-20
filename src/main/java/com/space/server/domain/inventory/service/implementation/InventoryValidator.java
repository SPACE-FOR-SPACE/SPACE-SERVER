package com.space.server.domain.inventory.service.implementation;

import com.space.server.domain.inventory.domain.repository.InventoryRepository;
import com.space.server.domain.inventory.exception.InventoryItemExistedException;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.exception.InsufficientPointsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryValidator {
  private final InventoryRepository inventoryRepository;

  public void hasItem(Item item, Users user) {
    if(inventoryRepository.existsByItemAndUser(item, user)) {
      throw new InventoryItemExistedException();
    }
  }
  
  public void buyItem(Item item, Users user) {
    if(item.getPrice() > user.getPoint()) {
      throw new InsufficientPointsException();
    }
    else user.payPoint(item.getPrice());
  }
}
