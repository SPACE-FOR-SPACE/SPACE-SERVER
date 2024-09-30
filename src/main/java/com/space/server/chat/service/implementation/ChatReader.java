package com.space.server.chat.service.implementation;

import com.space.server.chat.domain.repository.ChatRepository;
import com.space.server.state.domain.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatReader {

    private final ChatRepository chatRepository;

    public Integer findMaxOrderByState(State state) {
        return chatRepository.findMaxOrderByState(state);
    }
}
