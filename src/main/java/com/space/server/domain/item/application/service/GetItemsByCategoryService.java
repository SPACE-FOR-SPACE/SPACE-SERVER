package com.space.server.domain.item.application.service;

import com.space.server.domain.common.UseCase;
import com.space.server.domain.item.application.port.in.GetItemsByCategoryQuery;
import com.space.server.domain.item.application.port.out.LoadItemsByCategoryPort;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.domain.value.Category;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetItemsByCategoryService implements GetItemsByCategoryQuery {

  private final LoadItemsByCategoryPort loadItemsByCategoryPort;

  @Override
  public List<Item> retrieveItemsByCategory(Category category) {
    return loadItemsByCategoryPort.loadItemsByCategory(category);
  }
}
