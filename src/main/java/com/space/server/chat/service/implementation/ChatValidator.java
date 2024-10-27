package com.space.server.chat.service.implementation;

import com.space.server.chat.service.badWord.BadWords;
import com.space.server.common.exception.ErrorCode;
import com.space.server.common.exception.SpaceException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ChatValidator extends HashSet<String> implements BadWords {

  public ChatValidator() {
    addAll(List.of(koreaWord));
  }

  public void validateEnglish(String chat) {
    Pattern pattern = Pattern.compile("[a-zA-Z]");
    Matcher matcher = pattern.matcher(chat);
    if (matcher.find()) {
      throw new SpaceException(ErrorCode.CHAT_NOT_ENGLISH);
    }
  }

  public void validateBadWords(String chat) {
    if(stream().anyMatch(chat::contains)) {
      throw new SpaceException(ErrorCode.CHAT_NOT_BAD_WORD);
    }
  }
}
