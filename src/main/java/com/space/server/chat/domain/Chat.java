package com.space.server.chat.domain;

import com.space.server.chat.domain.value.Type;
import com.space.server.state.domain.State;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    private String botChat;

    private String userChat;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Integer order;

    @Builder
    public Chat(State state, String botChat, String userChat, Type type, Integer order) {
        this.state = state;
        this.botChat = botChat;
        this.userChat = userChat;
        this.type = type;
        this.order = order;
    }



}
