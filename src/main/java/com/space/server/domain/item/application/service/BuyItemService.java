package com.space.server.domain.item.application.service;

import com.space.server.domain.common.UseCase;
import com.space.server.domain.item.application.port.in.BuyItemUseCase;
import com.space.server.domain.item.application.port.out.BuyItemPort;
import com.space.server.domain.item.application.port.out.LoadItemPort;
import com.space.server.domain.item.domain.Item;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.domain.repository.UserRepository;
import com.space.server.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class BuyItemService implements BuyItemUseCase {

  private final BuyItemPort buyItemPort;
  private final LoadItemPort loadItemPort;
  private final UserRepository userRepository;

  @Override
  public void buyItem(Long itemId, Long userId) {
    Item item = loadItemPort.loadItem(itemId);
    Users user = userRepository.findById(userId)
        .orElseThrow(UserNotFoundException::new);

    //구매 가능 여부 로직

    buyItemPort.buyItemPort(item, user);
  }
}
