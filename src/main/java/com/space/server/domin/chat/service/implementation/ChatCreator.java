package com.space.server.domin.chat.service.implementation;

import com.space.server.domin.chat.domain.Chat;
import com.space.server.domin.chat.domain.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatCreator {
    private final ChatRepository chatRepository;

    public void create(Chat chat){ chatRepository.save(chat); }
}
