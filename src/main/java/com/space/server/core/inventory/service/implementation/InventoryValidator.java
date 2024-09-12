package com.space.server.core.inventory.service.implementation;

import com.space.server.common.exception.ErrorCode;
import com.space.server.common.exception.SpaceException;
import com.space.server.core.inventory.domain.repository.InventoryRepository;
import com.space.server.core.item.domain.Item;
import com.space.server.core.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryValidator {
  private final InventoryRepository inventoryRepository;

  public void hasItem(Item item, User user) {
    if(inventoryRepository.existsByItemAndUser(item, user)) {
      throw new SpaceException(ErrorCode.INVENTORY_ITEM_FOUND);
    }
  }

  public void hasNotItem(Item item, User user) {
    if(!inventoryRepository.existsByItemAndUser(item, user)) {
      throw new SpaceException(ErrorCode.INVENTORY_ITEM_NOT_FOUND);
    }
  }
  
  public void canBuyItem(Item item, User user) {
    if(item.getPrice() > user.getPoint()) {
      throw new SpaceException(ErrorCode.INSUFFICIENT_POINTS);
    }
  }
}
