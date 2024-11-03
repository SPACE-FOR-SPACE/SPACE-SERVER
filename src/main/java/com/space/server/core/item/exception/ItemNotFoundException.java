package com.space.server.core.item.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends SpaceException {

  public ItemNotFoundException() {
    super(HttpStatus.NOT_FOUND, "ITEM_NOT_FOUND", "아이템을 찾을 수 없습니다.");
  }
}
