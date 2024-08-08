package com.space.server.core.item.service;

import com.space.server.core.item.domain.Item;
import com.space.server.core.item.service.implementation.ItemCreator;
import com.space.server.core.item.service.implementation.ItemDeleter;
import com.space.server.core.item.service.implementation.ItemReader;
import com.space.server.core.item.service.implementation.ItemUpdater;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandItemService {

  private final ItemCreator itemCreator;
  private final ItemDeleter itemDeleter;
  private final ItemUpdater itemUpdater;
  private final ItemReader itemReader;

  public void createItem(Item item) {
    itemCreator.create(item);
  }

  public void updateItem(Long itemId, Item item) {
    Item updatableItem = itemReader.read(itemId);
    itemUpdater.update(updatableItem, item);
  }

  public void deleteItem(Long itemId) {
    Item item = itemReader.read(itemId);
    itemDeleter.delete(item);
  }
}
