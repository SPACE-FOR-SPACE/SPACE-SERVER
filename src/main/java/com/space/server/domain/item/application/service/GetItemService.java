package com.space.server.domain.item.application.service;

import com.space.server.domain.item.application.port.in.GetItemQuery;
import com.space.server.domain.item.application.port.out.LoadItemPort;
import com.space.server.common.annotation.UseCase;
import com.space.server.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetItemService implements GetItemQuery {

  private final LoadItemPort loadItemPort;

  @Override
  public Item getItem(Long id) {
    return loadItemPort.loadItem(id);
  }
}
