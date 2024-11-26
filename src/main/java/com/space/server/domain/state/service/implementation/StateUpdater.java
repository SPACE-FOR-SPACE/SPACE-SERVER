package com.space.server.domain.state.service.implementation;

import com.space.server.domain.state.domain.State;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StateUpdater {

    public void update(State updatableState, State state) {
        updatableState.update(state);
    }

    public void updateSuccess(State updatableState, LocalDateTime firstChatTime, LocalDateTime lastChatTime, Integer successCount) {
        updatableState.updateSuccess(firstChatTime, lastChatTime, successCount);
    }
}
