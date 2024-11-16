package com.space.server.domain.item.application.port.out;

import com.space.server.domain.item.domain.Item;
import com.space.server.domain.item.domain.value.Category;

import java.util.List;

public interface LoadItemsByCategoryPort {

  List<Item> loadItemsByCategory(Category category);
}
