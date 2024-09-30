package com.space.server.state.domain;

import com.space.server.core.quiz.domain.Quiz;
import com.space.server.state.domain.value.Status;
import com.space.server.user.domain.Users;
import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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

    @Type(IntArrayType.class)
    @Column(columnDefinition = "int[][]")
    private Integer[][] map;

    private Double score;

    @Builder(builderMethodName = "createBuilder")
    public State(Users user, Quiz quiz, Status status, Integer[][] map, Double score) {
        this.user = user;
        this.quiz = quiz;
        this.status = status;
        this.map = map;
        this.score = score;
    }

    @Builder(builderMethodName = "updateBuilder")
    public State(Status status, Integer[][] map, Double score) {
        this.status = status;
        this.map = map;
        this.score = score;
    }

    public void update(State state) {
        this.status = state.getStatus();
        this.map = new Integer[7][7];
        for (int i = 0; i < 7; i++) {
            System.arraycopy(state.getMap()[i], 0, this.map[i], 0, 7);
        }
        this.score = state.getScore();
    }



}