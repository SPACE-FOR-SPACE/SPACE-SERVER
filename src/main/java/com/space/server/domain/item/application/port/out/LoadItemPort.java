package com.space.server.domain.item.application.port.out;

import com.space.server.domain.item.domain.Item;

public interface LoadItemPort {

  Item loadItem(Long id);
}
