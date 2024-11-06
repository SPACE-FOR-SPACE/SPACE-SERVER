package com.space.server.domain.state.service.implementation;

import com.space.server.domain.state.domain.State;
import org.springframework.stereotype.Service;

@Service
public class StateUpdater {

    public void update(State updatableState, State state) {
        updatableState.update(state);
    }
}
