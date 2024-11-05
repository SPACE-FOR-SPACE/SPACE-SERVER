package com.space.server.state.service.implementation;

import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.state.domain.State;
import com.space.server.state.domain.repository.StateRepository;
import com.space.server.state.exception.StateNotFoundException;
import com.space.server.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
