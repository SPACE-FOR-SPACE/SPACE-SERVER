package com.space.server.domain.state.service.implementation;

import com.space.server.domain.state.domain.State;
import com.space.server.domain.state.domain.repository.StateRepository;
import com.space.server.domain.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateDeleter {

    private final StateRepository stateRepository;

    public void delete(State state) {
        stateRepository.delete(state);
    }

    public void deleteByUser(Users user) {
        stateRepository.deleteByUser(user);
    }
}
