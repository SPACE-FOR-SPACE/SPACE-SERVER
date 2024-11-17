package com.space.server.domain.mail.exception;

import com.space.server.common.exception.mail.SpaceMailException;
import org.springframework.http.HttpStatus;

public class EmailSendFailedException extends SpaceMailException {
  public EmailSendFailedException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, "EMAIL_SEND_FAILED", "이메일 전송에 실패했습니다.");
  }
}