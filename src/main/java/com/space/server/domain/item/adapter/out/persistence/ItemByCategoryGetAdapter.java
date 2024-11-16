package com.space.server.domain.item.adapter.out.persistence;

import com.space.server.domain.common.PersistenceAdapter;
import com.space.server.domain.item.application.port.out.LoadItemsByCategoryPort;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.domain.value.Category;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ItemByCategoryGetAdapter implements LoadItemsByCategoryPort {
  private final SpringDataItemRepository itemRepository;
  private final ItemMapper itemMapper;


  @Override
  public List<Item> loadItemsByCategory(Category category) {
    return itemRepository.findByCategory(category)
        .stream()
        .map(itemMapper::mapToDomainEntity)
        .toList();
  }
}
