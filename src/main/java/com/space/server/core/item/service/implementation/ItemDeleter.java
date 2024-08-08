package com.space.server.core.item.service.implementation;

import com.space.server.core.item.domain.Item;
import com.space.server.core.item.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemDeleter {

  private final ItemRepository itemRepository;

  public void delete(Item item) {
    itemRepository.delete(item);
  }
}