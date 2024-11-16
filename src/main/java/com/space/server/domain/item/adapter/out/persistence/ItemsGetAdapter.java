package com.space.server.domain.item.adapter.out.persistence;

import com.space.server.domain.common.PersistenceAdapter;
import com.space.server.domain.item.application.port.out.LoadItemsPort;
import com.space.server.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ItemsGetAdapter implements LoadItemsPort {

  private final SpringDataItemRepository itemRepository;
  private final ItemMapper itemMapper;

  @Override
  public List<Item> loadItems() {
    return itemRepository.findAll()
        .stream()
        .map(itemMapper::mapToDomainEntity)
        .toList();
  }
}