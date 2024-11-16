package com.space.server.domain.item.domain;

import com.space.server.domain.item.domain.value.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Item {

  private final ItemId id;

  @NotNull
  private final String name;

  @NotNull
  private final Integer price;

  @NotNull
  private final String image;

  @Enumerated(EnumType.STRING)
  @NotNull
  private final Category category;

  public static Item create(ItemId id, String name, Integer price, String image, Category category) {
    return new Item(id, name, price, image, category);
  }


  public Optional<ItemId> getId(){
    return Optional.ofNullable(this.id);
  }

  @Value
  public static class ItemId {
    private final Long value;
  }
}
