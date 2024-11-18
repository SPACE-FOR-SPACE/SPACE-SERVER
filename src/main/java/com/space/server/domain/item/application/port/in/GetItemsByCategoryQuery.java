package com.space.server.domain.item.application.port.in;

import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.domain.value.Category;

import java.util.List;

public interface GetItemsByCategoryQuery {

  List<Item> getItemsByCategory(Category category);
}
