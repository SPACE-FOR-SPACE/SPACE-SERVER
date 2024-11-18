package com.space.server.domain.inventory.adapter.in.web;

import com.space.server.domain.inventory.adapter.in.web.dto.response.InventoryResponse;
import com.space.server.domain.inventory.application.port.in.EquipInventoryUseCase;
import com.space.server.domain.inventory.application.port.in.GetInventoriesByIsEquippedQuery;
import com.space.server.domain.inventory.application.port.in.GetInventoriesQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventories")
@Tag(name = "Inventory", description = "인벤토리 API")
public class InventoryController {

  private final GetInventoriesQuery getInventoriesQuery;
  private final GetInventoriesByIsEquippedQuery getInventoriesByIsEquippedQuery;
  private final EquipInventoryUseCase equipInventoryUseCase;

  @GetMapping
  @Operation(summary = "전체 인벤토리 조회", description = "해당 유저의 전체 인벤토리를 조회합니다.")
  public List<InventoryResponse> readAll() {
    return getInventoriesQuery.getInventories(getMemberId()).stream()
        .map(InventoryResponse::from)
        .toList();
  }

  @GetMapping("/equip")
  @Operation(summary = "장착된 인벤토리 조회", description = "해당 유저의 장착된 인벤토리를 조회합니다.")
  public List<InventoryResponse> readIsEquipped() {
    return getInventoriesByIsEquippedQuery.getInventoriesByIsEquippedQuery(getMemberId()).stream()
        .map(InventoryResponse::from)
        .toList();
  }

  @PutMapping("/{inventory-id}")
  @Operation(summary = "인벤토리 장착", description = "해당 인벤토리를 장착합니다.")
  public void equipItem(@PathVariable(name = "inventory-id") @Parameter(description = "인벤토리 ID") Long inventoryId) {
    equipInventoryUseCase.equipInventory(inventoryId, getMemberId());
  }
}
