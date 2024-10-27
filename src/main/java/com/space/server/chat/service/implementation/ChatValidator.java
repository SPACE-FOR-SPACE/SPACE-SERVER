package com.space.server.chat.service.implementation;

import com.space.server.common.exception.ErrorCode;
import com.space.server.common.exception.SpaceException;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ChatValidator {

  public void checkEnglish(String chat) {
    Pattern pattern = Pattern.compile("[a-zA-Z]");
    Matcher matcher = pattern.matcher(chat);
    if (matcher.find()) {
      throw new SpaceException(ErrorCode.CHAT_NOT_ENGLISH);
    }
  }
}
