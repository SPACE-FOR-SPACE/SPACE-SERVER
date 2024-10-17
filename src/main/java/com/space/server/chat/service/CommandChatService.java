package com.space.server.chat.service;

import com.space.server.ai.service.dto.request.gpt.*;
import com.space.server.ai.service.dto.response.AiResponse;
import com.space.server.ai.service.dto.response.gpt.AiAllMessagesResponse;
import com.space.server.ai.service.dto.response.gpt.AiMessagesResponse;
import com.space.server.ai.service.dto.response.gpt.AiRunsResponse;
import com.space.server.ai.service.dto.response.gpt.AiThreadResponse;
import com.space.server.ai.service.implementation.ChatCompleter;
import com.space.server.ai.service.implementation.PromptCreator;
import com.space.server.chat.domain.Chat;
import com.space.server.chat.domain.value.Type;
import com.space.server.chat.presentation.dto.request.CreateChatRequest;
import com.space.server.chat.service.implementation.ChatCreator;
import com.space.server.chat.service.implementation.ChatReader;
import com.space.server.chat.service.implementation.AiResponseJsonParsing;
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
    private final AiResponseJsonParsing aiResponseJsonParsing;

    public AiResponse create(Long quizId, CreateChatRequest request, Long userId) {
        Quiz quiz = quizReader.findById(quizId);
        Users user = userReader.findById(userId);
        List<Checklist> checklists = checklistReader.findByQuiz(quiz);
        Chapter chapter = chapterReader.findById(quiz.getChapter().getId());

        Optional<State> state = stateReader.findByQuizIdAndUserId(quiz, user);

        PromptCreator promptCreator = new PromptCreator();
        AiChat aiChat = new AiChat("user", promptCreator.create(request.type(), quiz, checklists, chapter, request.userChat()));
        AiResponse botChat = null;

        // state 있다면 대화 로직
        if (state.isPresent()) {
            chatCompleter.messageCreate(state.get().getThreadId(), aiChat);
            AiRunsResponse aiRunsResponse = chatCompleter.runsCreate(state.get().getThreadId());

            while(true){
                if (chatCompleter.runsOneSelect(state.get().getThreadId(), aiRunsResponse.id()).status().equals("completed")){
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            AiAllMessagesResponse aiAllMessagesResponse = chatCompleter.messageAllSelect(state.get().getThreadId());
            AiMessagesResponse aiMessagesResponseSelect = chatCompleter.messageOneSelect(state.get().getThreadId(), aiAllMessagesResponse.first_id());

            Map<String, String> totalMapObject = new HashMap<>();
            totalMapObject.putAll(quiz.getMapObject());
            totalMapObject.putAll(chapter.getMapObject());

            log.info("AiChat : " + aiMessagesResponseSelect.content().get(0).text().value());
            botChat = aiResponseJsonParsing.jsonCreator(String.valueOf(aiMessagesResponseSelect.content().get(0).text().value()), totalMapObject);

            chatCreator.create(Chat.builder()
                    .state(state.get())
                    .userChat(request.userChat())
                    .botChat(botChat.feedback())
                    .type(Type.CODE)
                    .request_order(chatReader.findMaxOrderByState(state.get()) + 1)
                    .build());

            stateUpdater.update(State.updateBuilder()
                    .status(botChat.isSuccess() == true ? Status.SUCCESS : Status.FAIL)
                    .map(botChat.map())
                    .score(botChat.score())
                    .move(botChat.move())
                    .build(), state.get());
        }
        // 없다면 처음 만드는 로직
        else {
            AiThreadResponse aiThreadResponse = chatCompleter.threadCreate();
            chatCompleter.messageCreate(aiThreadResponse.id(), aiChat);
            AiRunsResponse aiRunsResponse = chatCompleter.runsCreate(aiThreadResponse.id());

            while(true){
                if (chatCompleter.runsOneSelect(aiThreadResponse.id(), aiRunsResponse.id()).status().equals("completed")){
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            AiAllMessagesResponse aiAllMessagesResponse = chatCompleter.messageAllSelect(aiThreadResponse.id());
            AiMessagesResponse aiMessagesResponseSelect = chatCompleter.messageOneSelect(aiThreadResponse.id(), aiAllMessagesResponse.first_id());

            Map<String, String> totalMapObject = new HashMap<>();
            totalMapObject.putAll(quiz.getMapObject());
            totalMapObject.putAll(chapter.getMapObject());

            log.info("AiChat : " + aiMessagesResponseSelect.content().get(0).text().value());
            botChat = aiResponseJsonParsing.jsonCreator(String.valueOf(aiMessagesResponseSelect.content().get(0).text().value()), totalMapObject);

            stateCreator.create(State.createBuilder()
                    .user(user)
                    .quiz(quiz)
                    .status(botChat.isSuccess() == true ? Status.SUCCESS : Status.FAIL)
                    .map(botChat.map())
                    .move(botChat.move())
                    .score(botChat.score())
                    .threadId(aiThreadResponse.id())
                    .build());
            chatCreator.create(Chat.builder()
                    .state(stateReader.findByQuizIdAndUserId(quiz, user).get())
                    .userChat(request.userChat())
                    .botChat(botChat.feedback())
                    .type(Type.CODE)
                    .request_order(chatReader.findMaxOrderByState(stateReader.findByQuizIdAndUserId(quiz, user).get()) + 1)
                    .build());
        }
        return botChat;
    }
}
