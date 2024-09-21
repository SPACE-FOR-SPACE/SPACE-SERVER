package com.space.server.auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Refresh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String refreshToken;

    private String expiration;

    @Builder
    public Refresh(String email, String refreshToken, String expiration) {
        this.email = email;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
