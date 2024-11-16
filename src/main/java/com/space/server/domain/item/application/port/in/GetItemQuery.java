package com.space.server.domain.item.application.port.in;

import com.space.server.domain.item.domain.Item;

public interface GetItemQuery {

  Item getItem(Long id);
}
