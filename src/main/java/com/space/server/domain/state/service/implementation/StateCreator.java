package com.space.server.domain.state.service.implementation;

import com.space.server.domain.state.domain.repository.StateRepository;
import com.space.server.domain.state.domain.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateCreator {

    private final StateRepository stateRepository;

    public void create(State state) {
        stateRepository.save(state);
    }
}
