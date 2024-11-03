package com.space.server.chat.service.implementation;

import com.space.server.chat.exception.ChatNotBadWordException;
import com.space.server.chat.exception.ChatNotEnglishException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ChatValidator {
  private final Set<String> badWords = new HashSet<>();
  ClassPathResource resource = new ClassPathResource("BadWords.txt");

  public ChatValidator() throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
      reader.lines().map(String::trim).forEach(badWords::add);
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
