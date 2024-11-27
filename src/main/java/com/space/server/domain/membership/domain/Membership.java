package com.space.server.domain.membership.domain;

import com.space.server.domain.membership.domain.value.Authority;
import com.space.server.domain.training.domain.Training;
import com.space.server.domain.user.domain.Users;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Membership {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id")
    private Training training;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Membership(Users user, Training training, Authority authority) {
        this.user = user;
        this.training = training;
        this.authority = authority;
    }


}
