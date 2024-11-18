package com.space.server.domain.state.service.implementation;

import com.space.server.domain.chat.service.implementation.ChatDeleter;
import com.space.server.domain.state.domain.State;
import com.space.server.domain.state.domain.repository.StateRepository;
import com.space.server.domain.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateDeleter {

    private final StateRepository stateRepository;
    private final ChatDeleter chatDeleter;
    private final StateReader stateReader;

    public void delete(State state) {
        chatDeleter.deleteByState(state);
        stateRepository.delete(state);
    }

    public void deleteByUser(Users user) {
        State deletableState = stateReader.findByUser(user);
        delete(deletableState);
    }
}
