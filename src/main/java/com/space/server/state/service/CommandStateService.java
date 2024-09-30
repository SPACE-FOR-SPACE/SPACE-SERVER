package com.space.server.state.service;

import com.space.server.state.domain.State;
import com.space.server.state.service.implementation.StateDeleter;
import com.space.server.state.service.implementation.StateReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandStateService {

    private final StateDeleter stateDeleter;
    private final StateReader stateReader;

    public void delete(Long stateId) {
        State state = stateReader.findById(stateId);
        stateDeleter.delete(state);
    }
}
