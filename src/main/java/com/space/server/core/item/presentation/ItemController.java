package com.space.server.core.item.presentation;

import com.space.server.core.inventory.service.CommandInventoryService;
import com.space.server.core.item.presentation.converter.CategoryConverter;
import com.space.server.core.item.presentation.dto.request.CreateItemRequest;
import com.space.server.core.item.presentation.dto.request.UpdateItemRequest;
import com.space.server.core.item.presentation.dto.response.ItemResponse;
import com.space.server.core.item.service.CommandItemService;
import com.space.server.core.item.service.QueryItemService;
import com.space.server.core.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/items")
public class ItemController {

  private final CommandItemService commandItemService;
  private final QueryItemService queryItemService;
  private final CommandInventoryService commandInventoryService;
  private final CategoryConverter categoryConverter;

  @PostMapping("/item")
  public void createItem(@RequestBody CreateItemRequest request) {
    commandItemService.createItem(request.toEntity());
  }

  @PostMapping("/{item-id}")
  public void buyItem(@PathVariable("item-id") Long itemId) {
    commandInventoryService.buyItem(itemId, new Users());
  }

  @GetMapping("/{item-id}")
  public ItemResponse readOne(@PathVariable("item-id") Long itemId) {
    return ItemResponse.from(queryItemService.readOne(itemId));
  }

  @GetMapping
  public List<ItemResponse> readAll() {
    return queryItemService.readAll().stream()
        .map(ItemResponse::from)
        .toList();
  }

  @GetMapping("/categories/{category}")
  public List<ItemResponse> findByCategory(@PathVariable("category") String category) {
    return queryItemService.findAllByCategory(categoryConverter.convert(category)).stream()
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