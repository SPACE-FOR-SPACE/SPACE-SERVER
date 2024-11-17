
package com.space.server.domain.item.application.port.out;

import com.space.server.domain.item.domain.Item;
import com.space.server.domain.user.domain.Users;

public interface BuyItemPort {

  void buyItemPort(Item item, Users user);
}
