package com.space.server.chat.service;

import com.space.server.ai.service.dto.request.gpt.*;
import com.space.server.ai.service.dto.response.AiResponse;
import com.space.server.ai.service.dto.response.gpt.AiMessagesResponse;
import com.space.server.ai.service.dto.response.gpt.AiThreadResponse;
import com.space.server.ai.service.implementation.ChatCompleter;
import com.space.server.ai.service.implementation.PromptCreator;
import com.space.server.chat.domain.Chat;
import com.space.server.chat.domain.value.Type;
import com.space.server.chat.presentation.dto.request.CreateChatRequest;
import com.space.server.chat.service.implementation.ChatCreator;
import com.space.server.chat.service.implementation.ChatReader;
import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.chapter.service.implementation.ChapterReader;
import com.space.server.core.checklist.domain.Checklist;
import com.space.server.core.checklist.service.implementation.ChecklistReader;
import com.space.server.core.quiz.domain.Quiz;
import com.space.server.core.quiz.service.implementation.QuizReader;
import com.space.server.state.domain.State;
import com.space.server.state.domain.value.Status;
import com.space.server.state.service.implementation.StateCreator;
import com.space.server.state.service.implementation.StateReader;
import com.space.server.state.service.implementation.StateUpdater;
import com.space.server.user.domain.Users;
import com.space.server.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommandChatService {

    private final StateReader stateReader;
    private final StateCreator stateCreator;
    private final QuizReader quizReader;
    private final UserReader userReader;
    private final ChatReader chatReader;
    private final ChatCreator chatCreator;
    private final ChecklistReader checklistReader;
    private final ChapterReader chapterReader;
    private final ChatCompleter chatCompleter;
    private final StateUpdater stateUpdater;

    public void create(Long quizId, CreateChatRequest request, Long userId) {
        Quiz quiz = quizReader.findById(quizId);
        Users user = userReader.findById(userId);
        List<Checklist> checklists = checklistReader.findByQuiz(quiz);
        Chapter chapter = chapterReader.findById(quiz.getChapter().getId());

        Optional<State> state = stateReader.findByQuizIdAndUserId(quiz, user);
        PromptCreator promptCreator = new PromptCreator();
        AiChat aiChat = new AiChat("user", promptCreator.create(request.type(), quiz, checklists, chapter, request.userChat()));

        // state 있다면 대화 로직
        if (state.isPresent()) {
            AiMessagesResponse aiMessagesResponseCreate = chatCompleter.messageCreate(user.getThreadId(), aiChat);
            chatCompleter.runsCreate(user.getThreadId());
            AiMessagesResponse aiMessagesResponseSelect = chatCompleter.messageSelect(user.getThreadId(), aiMessagesResponseCreate.id());

            AiResponse botChat = aiMessagesResponseSelect.content().get(1).text().value();

            chatCreator.create(Chat.builder()
                    .state(stateReader.findByQuizIdAndUserId(quiz, user).get())
                    .userChat(request.userChat())
                    .botChat(botChat.toString())
                    .type(Type.CODE)
                    .order(chatReader.findMaxOrderByState(state.get()) + 1)
                    .build());

            stateUpdater.update(State.createBuilder()
                    .user(user)
                    .quiz(quiz)
                    .status(botChat.isSuccess() == true ? Status.SUCCESS : Status.FAIL)
                    .map(botChat.map())
                    .score(botChat.consistency())
                    .move(botChat.move())
                    .build(), state.get());

            chatReader.findAllChatByStateId(state.get());
        }
        // 없다면 처음 만드는 로직
        else {
            AiThreadResponse aiThreadResponse = chatCompleter.threadCreate();
            AiMessagesResponse aiMessagesResponseCreate = chatCompleter.messageCreate(aiThreadResponse.id(), aiChat);
            chatCompleter.runsCreate(aiThreadResponse.id());
            AiMessagesResponse aiMessagesResponseSelect = chatCompleter.messageSelect(aiThreadResponse.id(), aiMessagesResponseCreate.id());

            AiResponse botChat = aiMessagesResponseSelect.content().get(1).text().value();

            stateCreator.create(State.createBuilder()
                    .user(user)
                    .quiz(quiz)
                    .status(botChat.isSuccess() == true ? Status.SUCCESS : Status.FAIL)
                    .map(botChat.map())
                    .move(botChat.move())
                    .score(botChat.consistency())
                    .build());
            chatCreator.create(Chat.builder()
                    .state(stateReader.findByQuizIdAndUserId(quiz, user).get())
                    .userChat(request.userChat())
                    .botChat(botChat.toString())
                    .type(Type.CODE)
                    .order(chatReader.findMaxOrderByState(state.get()) + 1)
                    .build());
        }
    }
}
