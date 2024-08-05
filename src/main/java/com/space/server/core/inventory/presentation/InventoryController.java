package com.space.server.core.inventory.presentation;

import com.space.server.core.inventory.presentation.dto.response.InventoryResponse;
import com.space.server.core.inventory.service.CommandInventoryService;
import com.space.server.core.inventory.service.QueryInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {

  private final CommandInventoryService commandInventoryService;
  private final QueryInventoryService queryInventoryService;

  @GetMapping("/{inventory-id}")
  public InventoryResponse readOne(@PathVariable("inventory-id") Long characgterId) {
    return InventoryResponse.from(queryInventoryService.fineOne(characgterId));
  }
}
