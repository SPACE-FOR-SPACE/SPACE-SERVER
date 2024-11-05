package com.space.server.domin.inventory.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class InventoryNotFoundException extends SpaceException {

  public InventoryNotFoundException() {
    super(HttpStatus.NOT_FOUND, "INVENTORY_NOT_FOUND", "인벤토리를 찾을 수 없습니다.");
  }
}
