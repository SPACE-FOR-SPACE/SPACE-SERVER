package com.space.server.domin.chapter.exception;

import com.space.server.common.exception.SpaceException;
import org.springframework.http.HttpStatus;

public class ChapterNotFoundException extends SpaceException {

  public ChapterNotFoundException() {
    super(HttpStatus.NOT_FOUND, "CHAPTER_NOT_FOUND", "챕터를 찾을 수 없습니다.");
  }
}
