package com.space.server.chat.service;

import com.space.server.ai.service.dto.request.gpt.AiChat;
import com.space.server.ai.service.dto.request.gpt.AiAssistantsRequest;
import com.space.server.ai.service.dto.request.ResponseFormat;
import com.space.server.ai.service.dto.request.gpt.AiThreadRequest;
import com.space.server.ai.service.dto.request.gpt.Tools;
import com.space.server.ai.service.dto.response.gpt.AiAssistantsResponse;
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



            chatCreator.create(Chat.builder()
                    .state(stateReader.findByQuizIdAndUserId(quiz, user).get())
                    .userChat(request.userChat())
                    .botChat()
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
            String instruction = "The return type is JSON. " +
                    "Now for the return related settings. " +
                    "The map is an array of 7*7 format. " +
                    "isSuccess is the correct answer if any of the problem conditions are true. " +
                    "Accuracy is the percentage of correct answers among the problem conditions.";

            List<Tools> tools = List.of();
            tools.add(new Tools("code_interpreter"));

            AiAssistantsRequest assistantsRequest = new AiAssistantsRequest(
                    instruction,
                    "user-123",
                    tools,
                    "gpt-4o mini"
            );
            chatCompleter.assistantsCreate(assistantsRequest);

            List<AiChat> aiChats = List.of();
            aiChats.add(new AiChat("user", request.userChat()));
            chatCompleter.threadCreate(new AiThreadRequest(aiChats));



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
