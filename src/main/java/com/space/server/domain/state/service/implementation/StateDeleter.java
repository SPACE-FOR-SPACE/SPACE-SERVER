package com.space.server.domain.state.service.implementation;

import com.space.server.domain.chat.service.implementation.ChatDeleter;
import com.space.server.domain.state.domain.State;
import com.space.server.domain.state.domain.repository.StateRepository;
import com.space.server.domain.user.domain.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
        stateReader.findByUserId(user).ifPresentOrElse(
                deletableState -> {
                    log.warn("State 삭제: " + deletableState);
                    delete(deletableState);
                },
                () -> log.warn("해당 유저에는 State가 없습니다.: " + user)
        );
    }
}
