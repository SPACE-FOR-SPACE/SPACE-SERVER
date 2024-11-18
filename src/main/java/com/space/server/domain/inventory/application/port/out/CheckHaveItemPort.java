package com.space.server.domain.inventory.application.port.out;

import com.space.server.domain.item.domain.Item;
import com.space.server.domain.user.domain.Users;

public interface CheckHaveItemPort {

  Boolean checkHaveItem(Item item, Users user);
}
