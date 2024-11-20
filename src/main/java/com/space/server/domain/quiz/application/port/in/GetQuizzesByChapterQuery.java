package com.space.server.domain.quiz.application.port.in;

import com.space.server.domain.quiz.domain.Quiz;

import java.util.List;

public interface GetQuizzesByChapterQuery {

  List<Quiz> getQuizzesByChapter(Long chapterId);
}
