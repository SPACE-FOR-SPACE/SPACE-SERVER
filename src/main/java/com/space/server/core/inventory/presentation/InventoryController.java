package com.space.server.core.inventory.presentation;

import com.space.server.core.inventory.presentation.dto.request.InventoryRequest;
import com.space.server.core.inventory.presentation.dto.response.InventoryResponse;
import com.space.server.core.inventory.service.CommandInventoryService;
import com.space.server.core.inventory.service.QueryInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventories")
public class InventoryController {

  private final CommandInventoryService commandInventoryService;
  private final QueryInventoryService queryInventoryService;

  @PostMapping("/inventory")
  @Operation(summary = "인벤토리 생성", description = "구매한 아이템을 보관할 인벤토리 생성합니다.")
  public void createInventory(
      @Parameter(description = "아이템 ID", required = true) @RequestBody InventoryRequest request) {
    commandInventoryService.createInventory(request.itemId(), getMemberId());
  }

  @GetMapping("/{inventory-id}")
  @Operation(summary = "인벤토리 조회", description = "구매한 해당 아이템을 조회합니다.")
  public InventoryResponse readOne(
      @Parameter(description = "조회할 인벤토리 ID", required = true) @PathVariable("inventory-id") Long inventoryId) {
    return InventoryResponse.from(queryInventoryService.readOne(inventoryId));
  }

  @GetMapping
  @Operation(summary = "전체 인벤토리 조회", description = "전체 구매한 아이템을 조회합니다.")
  public List<InventoryResponse> readAll() {
    return queryInventoryService.readMine(getMemberId()).stream()
        .map(InventoryResponse::from)
        .toList();
  }

  @GetMapping("/equip")
  @Operation(summary = "장착 인벤토리 조회", description = "장착된 아이템을 조회합니다.")
  public List<InventoryResponse> readIsEquipped() {
    return queryInventoryService.readIsEquipped(getMemberId()).stream()
        .map(InventoryResponse::from)
        .toList();
  }

  @PutMapping("/{inventory-id}")
  @Operation(summary = "아이템 장착", description = "해당 구매한 아이템을 장착합니다.")
  public void equipItem(
      @Parameter(description = "장착할 인벤토리 ID", required = true) @PathVariable(name = "inventory-id") Long inventoryId) {
    commandInventoryService.equipInventory(inventoryId, getMemberId());
  }

  @DeleteMapping("/{inventory-id}")
  @Operation(summary = "인벤토리 삭제", description = "구매한 아이템을 삭제합니다.")
  public void deleteInventory(
      @Parameter(description = "삭제할 인벤토리 ID", required = true) @PathVariable(name = "inventory-id") Long inventoryId) {
    commandInventoryService.deleteInventory(inventoryId);
  }
}
