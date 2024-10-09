package com.space.server.chat.presentation;

import com.space.server.ai.service.dto.response.AiResponse;
import com.space.server.chat.domain.Chat;
import com.space.server.chat.presentation.dto.request.CreateChatRequest;
import com.space.server.chat.service.CommandChatService;
import com.space.server.chat.service.QueryChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final CommandChatService commandChatService;
    private final QueryChatService queryChatService;


    @PostMapping("/chats/{quiz-id}")
    public AiResponse createChat(
            @PathVariable("quiz-id") Long quizId,
            @RequestBody CreateChatRequest request
            ) {
        return commandChatService.create(quizId, request, getMemberId());
    }


    // 주의 : 조회 시 유저랑 퀴즈가 다 날라감
    @GetMapping("/chats/{quiz-id}")
    public List<Chat> createChat(
            @PathVariable("quiz-id") Long quizId
    ) {
        return queryChatService.readChats(quizId, getMemberId());
    }
}
