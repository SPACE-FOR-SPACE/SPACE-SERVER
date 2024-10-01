package com.space.server.chat.service;

import com.space.server.ai.service.dto.request.AiChat;
import com.space.server.ai.service.dto.request.AiRequest;
import com.space.server.ai.service.dto.request.ResponseFormat;
import com.space.server.ai.service.dto.response.AiResponse;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public void create(Long quizId, CreateChatRequest request, Long userId, Type type) {
        Quiz quiz = quizReader.findById(quizId);
        Users user = userReader.findById(userId);
        List<Checklist> checklists = checklistReader.findByQuiz(quiz);
        Chapter chapter = chapterReader.findById(quiz.getChapter().getId());

        Optional<State> state = stateReader.findByQuizIdAndUserId(quiz, user);

        // state 있다면 대화 로직
        if (state.isPresent()) {
            List<Chat> chats = chatReader.findAllChatByStateId(state.get());
            PromptCreator promptCreator = new PromptCreator();

            promptCreator.create(type, quiz, checklists, chapter, request.userChat());

            List<AiChat> aiChats = List.of();
            aiChats.add(new AiChat("system", "The response format is JSON."));
            for (Chat chat : chats) {
                aiChats.add(new AiChat("user", chat.getUserChat()));
                aiChats.add(new AiChat("assistant", chat.getBotChat()));
            }
            AiRequest aiRequest = new AiRequest("llama-3.1-8b-instant", aiChats, 1F, 2048L, 1L, false, new ResponseFormat("json_object"), null);
            AiResponse aiResponse = chatCompleter.completerChat(aiRequest);

            chatCreator.create(Chat.builder()
                    .state(stateReader.findByQuizIdAndUserId(quiz, user).get())
                    .userChat(request.userChat())
                    .botChat(aiResponse.choices().message().toString())
                    .type(Type.CODE)
                    .order(1)
                    .build());

            stateUpdater.update(State.createBuilder()
                    .user(user)
                    .quiz(quiz)
                    .status()
                    .map()
                    .score()
                    .build(), state.get());

            chatReader.findAllChatByStateId(state.get());
        }
        // 없다면 처음 만드는 로직
        else {
            stateCreator.create(State.createBuilder()
                    .user(user)
                    .quiz(quiz)
                    .status(Status.FAIL)
                    .map(new Integer[7][7])
                    .score(0.0)
                    .build());
            chatCreator.create(Chat.builder()
                            .state(stateReader.findByQuizIdAndUserId(quiz, user).get())
                            .userChat(request.userChat())
                            .botChat("")
                            .type(Type.CODE)
                            .order(1)
                            .build());
        }
    }
}
