package com.space.server.domain.quiz.application.service;

import com.space.server.common.annotation.UseCase;
import com.space.server.domain.quiz.application.port.in.GetQuizzesByChapterQuery;
import com.space.server.domain.quiz.application.port.out.LoadQuizzesByChapterPort;
import com.space.server.domain.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetQuizzesByChapterService implements GetQuizzesByChapterQuery {

  private final LoadQuizzesByChapterPort loadQuizzesByChapterPort;

  @Override
  public List<Quiz> getQuizzesByChapter(Long chapterId) {
    return loadQuizzesByChapterPort.loadQuizzesByChapter(chapterId);
  }
}
