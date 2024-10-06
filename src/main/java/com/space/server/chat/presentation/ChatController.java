package com.space.server.chat.presentation;

import com.space.server.chat.domain.value.Type;
import com.space.server.chat.presentation.dto.request.CreateChatRequest;
import com.space.server.chat.service.CommandChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final CommandChatService commandChatService;
//    private final QueryChatService queryChatService;


    @PostMapping("/chats/{quiz-id}")
    public void createChat(
            @PathVariable("quiz-id") Long quizId,
            @RequestBody CreateChatRequest request
            ) {
        commandChatService.create(quizId, request, getMemberId());
    }
}
