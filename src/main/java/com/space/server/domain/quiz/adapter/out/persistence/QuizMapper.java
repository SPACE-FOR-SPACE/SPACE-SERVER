package com.space.server.domain.quiz.adapter.out.persistence;

import com.space.server.domain.chapter.adapter.out.persistence.ChapterMapper;
import com.space.server.domain.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuizMapper {

  private final ChapterMapper chapterMapper;

  public Quiz mapToQuiz(QuizJpaEntity quiz) {
    return Quiz.builder()
        .id(new Quiz.QuizId(quiz.getId()))
        .chapter(chapterMapper.mapToChapter(quiz.getChapter()))
        .stepId(quiz.getStepId())
        .title(quiz.getTitle())
        .content(quiz.getContent())
        .map(quiz.getMap())
        .characterDirection(quiz.getCharacterDirection())
        .mapObject(quiz.getMapObject())
        .mapObjectImage(quiz.getMapObjectImage())
        .build();
  }

  public QuizJpaEntity mapToQuizJpaEntity(Quiz quiz) {
    return QuizJpaEntity.builder()
        .id(quiz.getId() == null ? null : quiz.getId().getValue())
        .chapter(chapterMapper.mapToChapterJpaEntity(quiz.getChapter()))
        .stepId(quiz.getStepId())
        .title(quiz.getTitle())
        .content(quiz.getContent())
        .map(quiz.getMap())
        .characterDirection(quiz.getCharacterDirection())
        .mapObject(quiz.getMapObject())
        .mapObjectImage(quiz.getMapObjectImage())
        .build();
  }
}
