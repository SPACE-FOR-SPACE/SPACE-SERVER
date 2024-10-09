package com.space.server.chat.presentation;

import com.space.server.ai.service.dto.response.AiResponse;
import com.space.server.chat.domain.Chat;
import com.space.server.chat.presentation.dto.request.CreateChatRequest;
import com.space.server.chat.presentation.dto.response.ChatResponse;
import com.space.server.chat.service.CommandChatService;
import com.space.server.chat.service.QueryChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
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
    public List<ChatResponse> createChat(
            @PathVariable("quiz-id") Long quizId
    ) {
        return queryChatService.readChats(quizId, getMemberId());
    }

    // 상태, 점수만 보냄
    @GetMapping("/chats/{quiz-id}/result")
    public
}
