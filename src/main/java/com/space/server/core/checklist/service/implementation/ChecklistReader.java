package com.space.server.core.checklist.service.implementation;

import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.checklist.domain.repository.ChecklistRepository;
import com.space.server.core.checklist.exception.ChecklistNotFoundException;
import com.space.server.core.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChecklistReader {

  private final ChecklistRepository repository;

  public Checklist readOne(Long checklistId) {
    return repository.findById(checklistId)
        .orElseThrow(ChecklistNotFoundException::new);
  }

  public List<Checklist> readALL() {
    return repository.findAll();
  }

  public List<Checklist> findByQuiz(Quiz quiz) {
    return repository.findByQuiz(quiz);
  }
}
