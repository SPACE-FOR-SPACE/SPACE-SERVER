package com.space.server.domain.item.adapter.in.web;

import com.space.server.domain.item.adapter.in.web.converter.CategoryConverter;
import com.space.server.domain.item.adapter.in.web.dto.response.ItemResponse;
import com.space.server.domain.item.application.port.in.BuyItemUseCase;
import com.space.server.domain.item.application.port.in.GetItemQuery;
import com.space.server.common.annotation.WebAdapter;
import com.space.server.domain.item.application.port.in.GetItemsByCategoryQuery;
import com.space.server.domain.item.application.port.in.GetItemsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/items")
public class ItemController {

  private final GetItemQuery getItemQuery;
  private final GetItemsQuery getItemsQuery;
  private final GetItemsByCategoryQuery getItemsByCategoryQuery;
  private final BuyItemUseCase buyItemUseCase;
  private final CategoryConverter converter;

  @PostMapping("/{item-id}")
  public void buyItem(@PathVariable("item-id") Long itemId) {
    buyItemUseCase.buyItem(itemId, getMemberId());
  }

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
    return getItemsByCategoryQuery.getItemsByCategory(converter.convert(category)).stream()
        .map((ItemResponse::from))
        .toList();
  }
}
