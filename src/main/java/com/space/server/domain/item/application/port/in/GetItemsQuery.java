package com.space.server.domain.item.application.port.in;

import com.space.server.domain.item.domain.Item;

import java.util.List;

public interface GetItemsQuery {

  List<Item> getItems();
}
