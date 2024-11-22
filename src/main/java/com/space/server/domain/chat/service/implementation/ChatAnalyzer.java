package com.space.server.domain.chat.service.implementation;

import com.space.server.domain.chat.exception.ChatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.stereotype.Service;
import scala.collection.JavaConverters;
import scala.collection.Seq;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatAnalyzer {

  public List<String> analyzeText(String text) {

    if (text == null || text.isBlank()) {
      throw new ChatNotFoundException();
    }

    CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);

    Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);

    List<KoreanTokenizer.KoreanToken> tokenList = JavaConverters.seqAsJavaList(tokens);

    List<String> nouns = new ArrayList<>();
    for (KoreanTokenizer.KoreanToken token : tokenList) {
      if (token.pos().toString().equals("Noun")) {
        nouns.add(token.text());
      }
    }
    return nouns;
  }
}
