package com.space.server.domain.quiz.application.port.out;

import com.space.server.domain.quiz.domain.Quiz;

import java.util.List;

public interface LoadQuizzesByChapterPort {

  List<Quiz> loadQuizzesByChapter(Long chapterId);
}
