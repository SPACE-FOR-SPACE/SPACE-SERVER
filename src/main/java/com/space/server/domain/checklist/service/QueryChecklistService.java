package com.space.server.domain.checklist.service;

import com.space.server.domain.checklist.domain.Checklist;
import com.space.server.domain.checklist.service.implementation.ChecklistReader;
import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.quiz.service.implementation.QuizReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryChecklistService {

  private final ChecklistReader checklistReader;
  private final QuizReader quizReader;

  public Checklist readOne(Long checklistId) {
    return checklistReader.readOne(checklistId);
  }

  public List<Checklist> readAll() {
    return checklistReader.readALL();
  }

  public List<Checklist> findByQuiz(Long quizId) {
    Quiz quiz = quizReader.findById(quizId);
    return checklistReader.findByQuiz(quiz);
  }
}
