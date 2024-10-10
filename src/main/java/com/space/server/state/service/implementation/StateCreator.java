package com.space.server.state.service.implementation;

import com.space.server.state.domain.State;
import com.space.server.state.domain.repository.StateRepository;
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
