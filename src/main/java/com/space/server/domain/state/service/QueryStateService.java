package com.space.server.domain.state.service;

import com.space.server.domain.state.domain.State;
import com.space.server.domain.state.service.implementation.StateReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryStateService {

    private final StateReader stateReader;

    public State readOne(Long stateId) {
        return stateReader.findById(stateId);
    }
}
