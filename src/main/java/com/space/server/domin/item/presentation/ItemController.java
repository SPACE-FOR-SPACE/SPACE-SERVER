package com.space.server.domin.item.presentation;

import com.space.server.domin.inventory.service.CommandInventoryService;
import com.space.server.domin.item.presentation.converter.CategoryConverter;
import com.space.server.domin.item.presentation.dto.response.ItemResponse;
import com.space.server.domin.item.service.QueryItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/items")
@Tag(name = "Item", description = "아이템 API")
public class ItemController {

  private final QueryItemService queryItemService;
  private final CommandInventoryService commandInventoryService;
  private final CategoryConverter categoryConverter;

  @PostMapping("/{item-id}")
  @Operation(summary = "아이템 구매", description = "해당 아이템을 구매합니다.")
  public void buyItem(@PathVariable("item-id") @Parameter(description = "구매할 아이템 ID") Long itemId) {
    commandInventoryService.buyItem(itemId, getMemberId());
  }

  @GetMapping("/{item-id}")
  @Operation(summary = "아이템 조회", description = "해당 아이템을 조회합니다.")
  public ItemResponse readOne(@PathVariable("item-id") @Parameter(description = "조회할 아이템 ID") Long itemId) {
    return ItemResponse.from(queryItemService.readOne(itemId));
  }

  @GetMapping
  @Operation(summary = "아이템 전체 조회", description = "전체 아이템을 조회합니다.")
  public List<ItemResponse> readAll() {
    return queryItemService.readAll().stream()
        .map(ItemResponse::from)
        .toList();
  }

  @GetMapping("/categories/{category}")
  @Operation(summary = "카테고리별 아이템 조회", description = "카테고리별 아이템을 조회합니다.")
  public List<ItemResponse> findByCategory(@PathVariable("category") @Parameter(description = "조회할 카테고리") String category) {
    return queryItemService.findAllByCategory(categoryConverter.convert(category)).stream()
        .map(ItemResponse::from)
        .toList();
  }
}
