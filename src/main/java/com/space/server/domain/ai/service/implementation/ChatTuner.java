package com.space.server.domain.ai.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.server.domain.ai.service.dto.request.gpt.AiChat;
import com.space.server.domain.ai.service.dto.request.gpt.chatTune.AiChatTune;
import com.space.server.domain.ai.service.dto.response.AiResponse;
import com.space.server.domain.ai.service.dto.response.chatTune.AiChatTuneResponse;
import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.chapter.service.implementation.ChapterReader;
import com.space.server.domain.chat.domain.Chat;
import com.space.server.domain.chat.domain.value.Type;
import com.space.server.domain.chat.presentation.dto.request.CreateChatRequest;
import com.space.server.domain.chat.service.implementation.AiResponseJsonParsing;
import com.space.server.domain.chat.service.implementation.ChatCreator;
import com.space.server.domain.chat.service.implementation.ChatReader;
import com.space.server.domain.chat.service.implementation.ChatValidator;
import com.space.server.domain.checklist.domain.Checklist;
import com.space.server.domain.checklist.service.implementation.ChecklistReader;
import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.quiz.service.implementation.QuizReader;
import com.space.server.domain.state.domain.State;
import com.space.server.domain.state.domain.value.Status;
import com.space.server.domain.state.service.implementation.StateCreator;
import com.space.server.domain.state.service.implementation.StateReader;
import com.space.server.domain.state.service.implementation.StateUpdater;
import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.service.implementation.UserReader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Log4j2
@Service
@Transactional
public class    ChatTuner {
    @Value("${gpt.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final String baseUrl = "https://api.openai.com/v1/chat/completions";

    private final StateReader stateReader;
    private final StateCreator stateCreator;
    private final QuizReader quizReader;
    private final UserReader userReader;
    private final ChatReader chatReader;
    private final ChatCreator chatCreator;
    private final ChatValidator chatValidator;
    private final ChecklistReader checklistReader;
    private final ChapterReader chapterReader;
    private final StateUpdater stateUpdater;
    private final AiResponseJsonParsing aiResponseJsonParsing;

    private final String systemInstruction;

    public ChatTuner(@Value("${gpt.api.key}") String apiKey,
                     RestTemplate restTemplate,
                     StateReader stateReader,
                     StateCreator stateCreator,
                     QuizReader quizReader,
                     UserReader userReader,
                     ChatReader chatReader,
                     ChatCreator chatCreator,
                     ChatValidator chatValidator,
                     ChecklistReader checklistReader,
                     ChapterReader chapterReader,
                     StateUpdater stateUpdater,
                     AiResponseJsonParsing aiResponseJsonParsing) throws IOException {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
        this.stateReader = stateReader;
        this.stateCreator = stateCreator;
        this.quizReader = quizReader;
        this.userReader = userReader;
        this.chatReader = chatReader;
        this.chatCreator = chatCreator;
        this.chatValidator = chatValidator;
        this.checklistReader = checklistReader;
        this.chapterReader = chapterReader;
        this.stateUpdater = stateUpdater;
        this.aiResponseJsonParsing = aiResponseJsonParsing;

        // systemInstruction 초기화
        this.systemInstruction = loadSystemInstruction();
    }

    public AiResponse chatTuneCreator(Long quizId, CreateChatRequest request, Long userId) throws IOException {
        Quiz quiz = quizReader.findById(quizId);
        Users user = userReader.findById(userId);
        List<Checklist> checklists = checklistReader.findByQuiz(quiz);
        Chapter chapter = chapterReader.findById(quiz.getChapter().getId());
        Optional<State> state = stateReader.findByQuizIdAndUserId(quiz, user);

        String userChat = request.userChat();
        chatValidator.validateBadWords(userChat);
        chatValidator.validateEnglish(userChat);

        PromptCreator promptCreator = new PromptCreator();
        AiChat aiChat = new AiChat("user", promptCreator.create(request.type(), quiz, checklists, chapter, request.userChat()));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);

        List<AiChat> messages = new ArrayList<>();
        messages.add(new AiChat("system", systemInstruction));
        messages.add(new AiChat("user", aiChat.toString()));
        AiChatTune aiChatTune = new AiChatTune("gpt-4o-mini", messages, 0.5, 1.0);
        HttpEntity<AiChatTune> httpEntity = new HttpEntity<>(aiChatTune, headers);

        log.info("AI 로직 시작");
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            //completion.choices[0].message.content
            String responseBody = responseEntity.getBody();
            log.info("ResponseBody : " + responseBody);

            ObjectMapper objectMapper = new ObjectMapper();
            AiChatTuneResponse responseMap = objectMapper.readValue(responseBody, AiChatTuneResponse.class);
            log.info("ResponseMap : " + responseMap.toString());

            Map<String, String> totalMapObject = new HashMap<>();
            totalMapObject.putAll(quiz.getMapObject());
            totalMapObject.putAll(chapter.getMapObject());

            log.info("bot : " + responseMap.choices().get(0).message().content());
            AiResponse botChat = aiResponseJsonParsing.jsonCreator(String.valueOf(responseMap.choices().get(0).message().content()), totalMapObject);
            LocalDateTime lastChatTime = LocalDateTime.now();

            if(state.isPresent()){
                chatCreator.create(Chat.builder()
                        .state(state.get())
                        .userChat(request.userChat())
                        .botChat(botChat.feedback())
                        .type(Type.CODE)
                        .request_order(chatReader.findMaxOrderByState(state.get()) + 1)
                        .build());

                stateUpdater.update(State.updateBuilder()
                        .status(botChat.isSuccess() ? Status.SUCCESS : Status.FAIL)
                        .score(botChat.score())
                        .move(botChat.move())
                        .build(), state.get());

                if (state.get().getFirstTime() == null && botChat.isSuccess()) {
                    LocalDateTime firstChatTime = chatReader.findFirstChatTimeByState(state.get());
                    Integer successCount = chatReader.countChatByQuiz(state.get());
                    stateUpdater.updateSuccess(state.get(), firstChatTime, lastChatTime, successCount);
                }

            } else {
                stateCreator.create(State.createBuilder()
                        .user(user)
                        .quiz(quiz)
                        .status(botChat.isSuccess() ? Status.SUCCESS : Status.FAIL)
                        .move(botChat.move())
                        .score(botChat.score())
                        .threadId(null)
                        .build());
                chatCreator.create(Chat.builder()
                        .state(stateReader.findByQuizIdAndUserId(quiz, user).get())
                        .userChat(request.userChat())
                        .botChat(botChat.feedback())
                        .type(Type.CODE)
                        .request_order(chatReader.findMaxOrderByState(stateReader.findByQuizIdAndUserId(quiz, user).get()) + 1)
                        .build());

                if (botChat.isSuccess()) {
                    LocalDateTime firstChatTime = chatReader.findFirstChatTimeByState(stateReader.findByQuizIdAndUserId(quiz, user).get());
                    Integer successCount = chatReader.countChatByQuiz(stateReader.findByQuizIdAndUserId(quiz, user).get());
                    stateUpdater.updateSuccess(stateReader.findByQuizIdAndUserId(quiz, user).get(), firstChatTime, lastChatTime, successCount);
                }
            }

            return botChat;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private String loadSystemInstruction() throws IOException {
        ClassPathResource systemInstructionFile = new ClassPathResource("SystemInstruction.txt");
        StringBuilder systemInstruction = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(systemInstructionFile.getInputStream(), StandardCharsets.UTF_8))) {
            reader.lines().forEach(line -> systemInstruction.append(line).append(System.lineSeparator()));
        }

        return systemInstruction.toString().trim();
    }

}
