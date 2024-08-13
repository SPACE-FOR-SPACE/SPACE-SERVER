package com.space.server.core.item.presentation;

import com.space.server.core.inventory.presentation.dto.request.UpdateInventoryRequest;
import com.space.server.core.item.presentation.dto.request.CreateItemRequest;
import com.space.server.core.item.presentation.dto.request.UpdateItemRequest;
import com.space.server.core.item.presentation.dto.response.ItemResponse;
import com.space.server.core.item.service.CommandItemService;
import com.space.server.core.item.service.QueryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/items")
public class ItemController {

  private final CommandItemService commandItemService;
  private final QueryItemService queryItemService;

  @PostMapping("/item")
  public void createItem(@RequestBody CreateItemRequest request) {
    commandItemService.createItem(request.toEntity());
  }

  @GetMapping("/{item-id}")
  public ItemResponse readOne(@PathVariable("item-id") Long itemId) {
    return ItemResponse.from(queryItemService.readOne(itemId));
  }

  @GetMapping
  public List<ItemResponse> findAll() {
    return queryItemService.findAll().stream()
        .map(ItemResponse::from)
        .toList();
  }

  @GetMapping("/categories/{category}")
  public List<ItemResponse> findByCategory(@PathVariable("category") String category) {
    return queryItemService.findAllByCategory(category).stream()
        .map(ItemResponse::from)
        .toList();
  }

  @PutMapping("/{item-id}")
  public void updateItem(
      @PathVariable("item-id") Long itemId,
      @RequestBody UpdateItemRequest request
  ) {
      commandItemService.updateItem(itemId, request.toEntity());
  }

  @DeleteMapping("/{item-id}")
  public void deleteItem(@PathVariable("item-id") Long itemId) {
    commandItemService.deleteItem(itemId);
  }
}