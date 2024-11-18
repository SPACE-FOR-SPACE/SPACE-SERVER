package com.space.server.domain.inventory.application.service;

import com.space.server.common.annotation.UseCase;
import com.space.server.domain.inventory.application.port.in.GetInventoriesQuery;
import com.space.server.domain.inventory.application.port.out.LoadInventoriesPort;
import com.space.server.domain.inventory.domain.Inventory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetInventoriesService implements GetInventoriesQuery {

  private final LoadInventoriesPort loadInventoriesPort;

  @Override
  public List<Inventory> getInventories(Long userId) {
    return loadInventoriesPort.loadInventories(userId);
  }
}
