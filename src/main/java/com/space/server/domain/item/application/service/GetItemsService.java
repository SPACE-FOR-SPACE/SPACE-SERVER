package com.space.server.domain.item.application.service;

import com.space.server.common.annotation.UseCase;
import com.space.server.domain.item.application.port.in.GetItemsQuery;
import com.space.server.domain.item.application.port.out.LoadItemsPort;
import com.space.server.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetItemsService implements GetItemsQuery {

  private final LoadItemsPort loadItemsPort;

  @Override
  public List<Item> getItems() {
    return loadItemsPort.loadItems();
  }
}
