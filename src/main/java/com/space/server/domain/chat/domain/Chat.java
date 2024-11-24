package com.space.server.domain.chat.domain;

import com.space.server.domain.chat.domain.value.Type;
import com.space.server.domain.state.domain.State;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
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

    private Integer request_order;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Chat(State state, String botChat, String userChat, Type type, Integer request_order) {
        this.state = state;
        this.botChat = botChat;
        this.userChat = userChat;
        this.type = type;
        this.request_order = request_order;
    }
}
