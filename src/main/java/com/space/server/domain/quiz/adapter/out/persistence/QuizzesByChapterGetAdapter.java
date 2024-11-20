package com.space.server.domain.quiz.adapter.out.persistence;

import com.space.server.common.annotation.PersistenceAdapter;
import com.space.server.domain.chapter.adapter.out.persistence.ChapterJpaEntity;
import com.space.server.domain.chapter.adapter.out.persistence.ChapterRepository;
import com.space.server.domain.chapter.exception.ChapterNotFoundException;
import com.space.server.domain.quiz.application.port.out.LoadQuizzesByChapterPort;
import com.space.server.domain.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class QuizzesByChapterGetAdapter implements LoadQuizzesByChapterPort {

  private final ChapterRepository chapterRepository;
  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;

  @Override
  public List<Quiz> loadQuizzesByChapter(Long chapterId) {
    ChapterJpaEntity chapter = chapterRepository.findById(chapterId)
        .orElseThrow(ChapterNotFoundException::new);

    return quizRepository.findAllByChapterOrderByStepId(chapter).stream()
        .map(quizMapper::mapToQuiz)
        .toList();
  }
}
