package com.space.server.core.presentation.item;

import com.space.server.core.presentation.item.dto.response.ItemResponse;
import com.space.server.core.service.item.QueryItemService;
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
    return ItemResponse.of(queryItemService.findOne(id));
  }

  @GetMapping
  public List<ItemResponse> findAll() {
    return queryItemService.findAll().stream()
        .map(ItemResponse::of)
        .toList();
  }

  @GetMapping("{category}")
  public List<ItemResponse> findByCategory(@PathVariable("category") String category) {
    return queryItemService.findAllByCategory(category).stream()
        .map(ItemResponse::of)
        .toList();
  }
}