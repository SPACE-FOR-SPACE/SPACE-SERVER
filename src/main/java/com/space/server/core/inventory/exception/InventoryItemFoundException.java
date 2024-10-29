package com.space.server.core.inventory.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class InventoryItemFoundException extends SpaceException {

  public InventoryItemFoundException() {
    super(HttpStatus.FOUND, "아이템을 이미 가지고 있습니다.");
  }
}
