package com.space.server.user.domain;

import com.space.server.user.domain.value.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String type;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Integer age;

    private Integer point;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder(builderMethodName = "normalUserBuilder")
    public Users(String username, String password, String email, String type, Integer age, Role role) {
        this.role = role;
        this.username = username;
        this.type = type;
        this.email = email;
        this.password = password;
        this.age = age;
        this.point = 0;
    }

    @Builder(builderMethodName = "socialUserBuilder")
    public Users(String email, Role role, String type) {
        this.type = type;
        this.email = email;
        this.role = role;
        this.point = 0;
    }

    @Builder(builderMethodName = "jwtUserBuilder")
    public Users(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void updateSocial(String email, String type) {
        this.email = email;
        this.type = type;
    }

    public void updateAdditionalInfo(Integer age, String username) {
        this.age = age;
        this.username = username;
    }

    public void updateRole(Role role) {
        this.role = role;
    }

    public void payPoint(Integer point) {
        this. point -= point;
    }
}
