package com.space.server.domain.item.adapter.in.web;

import com.space.server.domain.item.adapter.in.web.converter.CategoryConverter;
import com.space.server.domain.item.adapter.in.web.dto.response.ItemResponse;
import com.space.server.domain.item.application.port.in.GetItemQuery;
import com.space.server.domain.common.WebAdapter;
import com.space.server.domain.item.application.port.in.GetItemsByCategoryQuery;
import com.space.server.domain.item.application.port.in.GetItemsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/items")
public class ItemController {

  private final GetItemQuery getItemQuery;
  private final GetItemsQuery getItemsQuery;
  private final GetItemsByCategoryQuery getItemsByCategoryQuery;
  private final CategoryConverter converter;

  //아이템 구매

  @GetMapping("/{item-id}")
  public ItemResponse readOne(@PathVariable("item-id") Long itemId) {
    return ItemResponse.from(getItemQuery.getItem(itemId));
  }

  @GetMapping()
  public List<ItemResponse> readAll() {
    return getItemsQuery.getItems().stream()
        .map(ItemResponse::from)
        .toList();
  }

  @GetMapping("/categories/{category}")
  public List<ItemResponse> readAllByCategory(@PathVariable("category") String category) {
    return getItemsByCategoryQuery.retrieveItemsByCategory(converter.convert(category)).stream()
        .map((ItemResponse::from))
        .toList();
  }
}
