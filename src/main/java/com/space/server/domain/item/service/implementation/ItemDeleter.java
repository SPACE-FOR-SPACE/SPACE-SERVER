package com.space.server.domain.item.service.implementation;

import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.domain.repository.ItemRepository;
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