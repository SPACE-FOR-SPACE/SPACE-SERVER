package com.space.server.auth.domain;

import com.space.server.user.domain.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Refresh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long userId;

    private String refreshToken;

    private String expiration;

    @Builder
    public Refresh(Long userId, String refreshToken, String expiration) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
