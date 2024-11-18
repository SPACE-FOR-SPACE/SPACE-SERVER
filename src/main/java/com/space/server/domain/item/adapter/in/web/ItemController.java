package com.space.server.domain.item.adapter.in.web;

import com.space.server.domain.item.adapter.in.web.converter.CategoryConverter;
import com.space.server.domain.item.adapter.in.web.dto.response.ItemResponse;
import com.space.server.domain.item.application.port.in.BuyItemUseCase;
import com.space.server.domain.item.application.port.in.GetItemQuery;
import com.space.server.common.annotation.WebAdapter;
import com.space.server.domain.item.application.port.in.GetItemsByCategoryQuery;
import com.space.server.domain.item.application.port.in.GetItemsQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/items")
@Tag(name = "item", description = "아이템 API")
public class ItemController {

  private final GetItemQuery getItemQuery;
  private final GetItemsQuery getItemsQuery;
  private final GetItemsByCategoryQuery getItemsByCategoryQuery;
  private final BuyItemUseCase buyItemUseCase;
  private final CategoryConverter converter;

  @PostMapping("/{item-id}")
  @Operation(summary = "해당 아이템 구매", description = "해당 아이템을 구매합니다.")
  public void buyItem(@PathVariable("item-id") @Parameter(description = "아이템 ID") Long itemId) {
    buyItemUseCase.buyItem(itemId, getMemberId());
  }

  @GetMapping("/{item-id}")
  @Operation(summary = "해당 아이템 조회", description = "해당 아이템을 조회합니다.")
  public ItemResponse readOne(@PathVariable("item-id") @Parameter(description = "아이템 ID") Long itemId) {
    return ItemResponse.from(getItemQuery.getItem(itemId));
  }

  @GetMapping()
  @Operation(summary = "전체 아이템 조회", description = "전체 아이템을 조회합니다.")
  public List<ItemResponse> readAll() {
    return getItemsQuery.getItems().stream()
        .map(ItemResponse::from)
        .toList();
  }

  @GetMapping("/categories/{category}")
  @Operation(summary = "카테고리별 아이템 전체 조회", description = "해당 카테고리의 아이템들을 조회합니다.")
  public List<ItemResponse> readAllByCategory(@PathVariable("category") @Parameter(description = "아이템 카테고리") String category) {
    return getItemsByCategoryQuery.getItemsByCategory(converter.convert(category)).stream()
        .map((ItemResponse::from))
        .toList();
  }
}
