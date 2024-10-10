package com.space.server.state.service.implementation;

import com.space.server.state.domain.State;
import com.space.server.state.domain.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateDeleter {

    private final StateRepository stateRepository;

    public void delete(State state) {
        stateRepository.delete(state);
    }
}
