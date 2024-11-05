package com.space.server.domin.chat.service.implementation;

import com.space.server.domin.chat.domain.Chat;
import com.space.server.domin.chat.domain.repository.ChatRepository;
import com.space.server.state.domain.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatReader {

    private final ChatRepository chatRepository;

    public Integer findMaxOrderByState(State state) {
        return chatRepository.findMaxOrderByState(state);
    }

    public List<Chat> findAllChatByState(State state) { return chatRepository.findAllChatByState(state); }
}
