package com.space.server.state.service.implementation;

import com.space.server.state.domain.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class StateUpdater {

    public void update(State updatableState, State state) {
        updatableState.update(state);
    }
}
