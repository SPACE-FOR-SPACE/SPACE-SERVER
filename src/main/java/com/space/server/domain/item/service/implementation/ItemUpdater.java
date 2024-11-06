package com.space.server.domain.item.service.implementation;

import com.space.server.domain.item.domain.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemUpdater {

  public void update(Item updatableItem, Item item) {
    updatableItem.update(item);
  }
}