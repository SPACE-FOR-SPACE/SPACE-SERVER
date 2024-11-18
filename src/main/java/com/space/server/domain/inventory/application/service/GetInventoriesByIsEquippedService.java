package com.space.server.domain.inventory.application.service;

import com.space.server.common.annotation.UseCase;
import com.space.server.domain.inventory.application.port.in.GetInventoriesByIsEquippedQuery;
import com.space.server.domain.inventory.application.port.out.LoadInventoriesByIsEquippedPort;
import com.space.server.domain.inventory.domain.Inventory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetInventoriesByIsEquippedService implements GetInventoriesByIsEquippedQuery {

  private final LoadInventoriesByIsEquippedPort loadInventoriesByIsEquippedPort;

  @Override
  public List<Inventory> getInventoriesByIsEquippedQuery(Long userId) {
    return loadInventoriesByIsEquippedPort.loadInventoriesByIsEquippedPort(userId);
  }
}
