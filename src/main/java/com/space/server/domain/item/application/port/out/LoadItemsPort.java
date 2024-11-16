package com.space.server.domain.item.application.port.out;

import com.space.server.domain.item.domain.Item;

import java.util.List;

public interface LoadItemsPort {

  List<Item> loadItems();
}
