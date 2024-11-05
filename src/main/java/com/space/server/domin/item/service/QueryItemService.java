package com.space.server.domin.item.service;

import com.space.server.domin.item.domain.Item;
import com.space.server.domin.item.domain.value.Category;
import com.space.server.domin.item.service.implementation.ItemReader;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryItemService {

  private final ItemReader itemReader;

  public List<Item> findAllByCategory(Category category) {
    return itemReader.findAllByCategory(category);
  }

  public List<Item> readAll() {
    return itemReader.findAll();
  }

  public Item readOne(Long itemId) {
    return itemReader.findById(itemId);
  }
}
