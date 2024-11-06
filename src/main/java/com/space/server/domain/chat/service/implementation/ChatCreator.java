package com.space.server.domain.chat.service.implementation;

import com.space.server.domain.chat.domain.Chat;
import com.space.server.domain.chat.domain.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatCreator {
    private final ChatRepository chatRepository;

    public void create(Chat chat){ chatRepository.save(chat); }
}
