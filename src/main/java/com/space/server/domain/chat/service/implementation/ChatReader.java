package com.space.server.domain.chat.service.implementation;

import com.space.server.domain.chat.domain.Chat;
import com.space.server.domain.chat.domain.repository.ChatRepository;
import com.space.server.domain.state.domain.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatReader {

    private final ChatRepository chatRepository;

    public Integer findMaxOrderByState(State state) {
        return chatRepository.findMaxOrderByState(state);
    }

    public List<Chat> findAllChatByState(State state) { return chatRepository.findAllChatByState(state); }

    public Integer countChatByQuiz(State state) {
        return chatRepository.countByState(state);
    }

    public LocalDateTime findFirstChatTimeByState(State state) {
        return chatRepository.findFirstByStateOrderByCreatedAtAsc(state).getCreatedAt();
    }

    public LocalDateTime findLastChatTimeByState(State state) {
        return chatRepository.findFirstByStateOrderByCreatedAtDesc(state).getCreatedAt();
    }
}
