package com.space.server.core.item.presentation;

import com.space.server.core.item.presentation.dto.response.ItemResponse;
import com.space.server.core.item.service.QueryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/items")
public class ItemController {

  private final QueryItemService queryItemService;

  @GetMapping("/{item-id}")
  public ItemResponse getItem(@PathVariable("item-id") Long id) {
    return ItemResponse.from(queryItemService.findOne(id));
  }

  @GetMapping
  public List<ItemResponse> findAll() {
    return queryItemService.findAll().stream()
        .map(ItemResponse::from)
        .toList();
  }

  @GetMapping("{category}")
  public List<ItemResponse> findByCategory(@PathVariable("category") String category) {
    return queryItemService.findAllByCategory(category).stream()
        .map(ItemResponse::from)
        .toList();
  }
}