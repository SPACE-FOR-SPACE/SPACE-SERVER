package com.space.server.domain.item.application.port.in;

public interface BuyItemUseCase {

  void buyItem(Long itemId, Long userId);
}

