package com.space.server.domain.item.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.item.application.port.out.LoadItemsPort;
import com.space.server.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ItemsGetAdapter implements LoadItemsPort {

  private final ItemRepository itemRepository;
  private final ItemMapper itemMapper;

  @Override
  public List<Item> loadItems() {
    return itemRepository.findAll().stream()
        .map(itemMapper::mapToItem)
        .toList();
  }
}
