package com.space.server.domain.chat.service.implementation;

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
    CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);

    // 토큰화
    Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);

    // Scala Seq를 Java List로 변환
    List<KoreanTokenizer.KoreanToken> tokenList = JavaConverters.seqAsJavaList(tokens);

    // 형태소 분석 및 명사 추출
    List<String> nouns = new ArrayList<>();
    for (KoreanTokenizer.KoreanToken token : tokenList) {
      if (token.pos().toString().equals("Noun")) {
        nouns.add(token.text());
      }
    }
    return nouns;
  }
}
