package com.space.server.domain.checklist.service.implementation;

import com.space.server.domain.checklist.domain.Checklist;
import com.space.server.domain.checklist.domain.repository.ChecklistRepository;
import com.space.server.domain.checklist.exception.ChecklistNotFoundException;
import com.space.server.domain.quiz.domain.Quiz;
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
