package com.space.server.domain.inventory.presentation;

import com.space.server.domain.inventory.presentation.dto.response.InventoryResponse;
import com.space.server.domain.inventory.service.CommandInventoryService;
import com.space.server.domain.inventory.service.QueryInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/inventories")
@Tag(name = "Inventory", description = "인벤토리 API")
public class InventoryController {

  private final CommandInventoryService commandInventoryService;
  private final QueryInventoryService queryInventoryService;

  @GetMapping
  @Operation(summary = "전체 인벤토리 조회", description = "전체 구매한 아이템을 조회합니다.")
  public List<InventoryResponse> readAll(HttpServletRequest request) {
    log.warn("인벤토리 조회 로그(MemberId) : " + getMemberId());
    log.warn("인벤토리 조회 로그(HttpServletRequest,cookie) : " + request.getCookies());
    log.warn("인벤토리 조회 로그(HttpServletRequest,cookie) : " + request.getRequestURI());
    log.warn("인벤토리 조회 로그(HttpServletRequest,cookie) : " + request.getUserPrincipal());
    log.warn("인벤토리 조회 로그(HttpServletRequest,cookie) : " + request.getMethod());
    log.warn("인벤토리 조회 로그(HttpServletRequest,cookie) : " + request.getServletPath());
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
}
