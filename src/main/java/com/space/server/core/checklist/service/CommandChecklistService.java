package com.space.server.core.checklist.service;

import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.chapter.service.implementation.ChapterReader;
import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.checklist.presentation.dto.request.ChecklistRequest;
import com.space.server.core.checklist.service.implementation.ChecklistCreator;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.service.implementation.QuizReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandChecklistService {

  private final ChecklistCreator checklistCreator;
  private final QuizReader quizReader;
  private final ChapterReader chapterReader;

  public void createChecklist(ChecklistRequest request) {
    Quiz quiz = quizReader.findById(request.quizId());
    Chapter chapter = chapterReader.findById(request.chapterId());
    Checklist checklist = new Checklist(quiz, chapter, request.content());
    checklistCreator.create(checklist);
  }
}
