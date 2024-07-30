package com.space.server.core.service.item;

import com.space.server.core.domain.item.Item;
import com.space.server.core.service.implementation.item.ItemReader;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryItemService {

  private final ItemReader itemReader;

  public List<Item> findAllByCategory(String category) {
    return itemReader.readAllByCategory(category);
  }

  public List<Item> findAll() {
    return itemReader.readAll();
  }

  public Item findOne(Long itemId) {
    return itemReader.read(itemId);
  }
}
