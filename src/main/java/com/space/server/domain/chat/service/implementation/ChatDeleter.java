package com.space.server.domain.chat.service.implementation;

import com.space.server.domain.chat.domain.repository.ChatRepository;
import com.space.server.domain.state.domain.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatDeleter {

    private final ChatRepository chatRepository;

    public void deleteByState(State state) {
        chatRepository.deleteByState(state);
    }
}
