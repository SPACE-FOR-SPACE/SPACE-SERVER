package com.space.server.domain.state.service.implementation;

import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.state.domain.repository.StateRepository;
import com.space.server.domain.state.domain.State;
import com.space.server.domain.state.exception.StateNotFoundException;
import com.space.server.domain.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StateReader {

    private final StateRepository stateRepository;

    public State findById(Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(StateNotFoundException::new);
    }

    public Optional<State> findByQuizIdAndUserId(Quiz quiz, Users user) {
        return stateRepository.findByQuizAndUser(quiz, user);
    }

    public List<State> findByChapterUserId(Chapter chapter, Users user) {
        return stateRepository.findByChapterAndUser(chapter, user);
    }

    public List<State> findByUserId(Users user) {
        return stateRepository.findByUser(user);
    }

    public List<State> findAllByUser(Users user) {
        return stateRepository.findAllByUser(user);
    }
}
