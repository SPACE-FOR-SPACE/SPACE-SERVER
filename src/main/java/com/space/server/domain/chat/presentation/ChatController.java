package com.space.server.domain.chat.presentation;

import com.space.server.domain.ai.service.dto.response.AiResponse;
import com.space.server.domain.chat.presentation.dto.request.CreateChatRequest;
import com.space.server.domain.chat.presentation.dto.response.ChatResponse;
import com.space.server.domain.chat.service.CommandChatService;
import com.space.server.domain.chat.service.QueryChatService;
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

    // 봇 챗, 유저 챗, 요청 순서만 보냄
    @GetMapping("/chats/{quiz-id}")
    public List<ChatResponse> readChats(
            @PathVariable("quiz-id") Long quizId
    ) {
        return queryChatService.readChats(quizId, getMemberId());
    }

    @GetMapping("/visualizations/chats/{quiz-id}")
    public List<String> readMostKeyWords(
        @PathVariable("quiz-id") Long quizId
    ) {
        return queryChatService.readMostKeyWords(quizId, getMemberId());
    }
}
