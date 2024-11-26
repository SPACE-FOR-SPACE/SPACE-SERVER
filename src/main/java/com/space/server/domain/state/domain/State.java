package com.space.server.domain.state.domain;

import com.space.server.domain.quiz.domain.Quiz;
import com.space.server.domain.state.domain.value.Status;
import com.space.server.domain.user.domain.Users;
import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime firstTime;

    private LocalDateTime lastTime;

    private Integer successCount;

    @Type(StringArrayType.class)
    @Column(columnDefinition = "varchar[]")
    private String[] move;

    @Type(IntArrayType.class)
    @Column(columnDefinition = "bigint[]")
    private Long[] score;

    private String threadId;

    @Builder(builderMethodName = "createBuilder")
    public State(Users user, Quiz quiz, Status status, String[] move, Long[] score, String threadId) {
        this.user = user;
        this.quiz = quiz;
        this.status = status;
        this.score = score;
        this.threadId = threadId;
        this.move = move;
    }

    @Builder(builderMethodName = "updateBuilder")
    public State(Status status, String[] move, Long[] score) {
        this.status = status;
        this.score = score;
        this.move = move;
    }

    public void update(State state) {
        this.status = state.getStatus();
        this.move = state.getMove();
        this.score = state.getScore();
    }

    public void updateSuccess(LocalDateTime firstTime, LocalDateTime lastChatTime, Integer successCount) {
        this.firstTime = firstTime;
        this.lastTime = lastChatTime;
        this.successCount = successCount;
    }
}
