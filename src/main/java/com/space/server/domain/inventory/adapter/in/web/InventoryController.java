package com.space.server.domain.inventory.adapter.in.web;

import com.space.server.domain.inventory.adapter.in.web.dto.response.InventoryResponse;
import com.space.server.domain.inventory.application.port.in.EquipInventoryUseCase;
import com.space.server.domain.inventory.application.port.in.GetInventoriesByIsEquippedQuery;
import com.space.server.domain.inventory.application.port.in.GetInventoriesQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;


@RestController
@RequiredArgsConstructor
@RequestMapping("/inventories")
public class InventoryController {

  private final GetInventoriesQuery getInventoriesQuery;
  private final GetInventoriesByIsEquippedQuery getInventoriesByIsEquippedQuery;
  private final EquipInventoryUseCase equipInventoryUseCase;

  @GetMapping
  public List<InventoryResponse> readAll() {
    return getInventoriesQuery.getInventories(getMemberId()).stream()
        .map(InventoryResponse::from)
        .toList();
  }

  @GetMapping("/equip")
  public List<InventoryResponse> readIsEquipped() {
    return getInventoriesByIsEquippedQuery.getInventoriesByIsEquippedQuery(getMemberId()).stream()
        .map(InventoryResponse::from)
        .toList();
  }

  @PutMapping("/{inventory-id}")
  public void equipItem(@PathVariable(name = "inventory-id") Long inventoryId) {
    equipInventoryUseCase.equipInventory(inventoryId, getMemberId());
  }
}
