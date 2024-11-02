package com.space.server.chat.service.implementation;

import com.space.server.chat.exception.ChatNotBadWordException;
import com.space.server.chat.exception.ChatNotEnglishException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ChatValidator {
  private final Set<String> badWords = new HashSet<>();

  public ChatValidator(@Value("${block.bad-words}") String filePath) {
    loadBadWords(filePath);
  }

  private void loadBadWords(String filePath) {
    try {
      List<String> koreanBadWords = Files.readAllLines(Path.of(filePath));
      badWords.addAll(koreanBadWords);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to load bad words file: " + e.getMessage(), e);
    }
  }


  public void validateEnglish(String chat) {
    Pattern pattern = Pattern.compile("[a-zA-Z]");
    Matcher matcher = pattern.matcher(chat);
    if (matcher.find()) {
      throw new ChatNotEnglishException();
    }
  }

  public void validateBadWords(String chat) {
    if(badWords.stream().anyMatch(chat::contains)) {
      throw new ChatNotBadWordException();
    }
  }
}
