package com.space.server.domain.user.service.implementation;

import com.space.server.domain.user.domain.Users;
import com.space.server.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;

    public void update(Users updatableUser, Users user) {

        updatableUser.updateAdditionalInfo(user.getAge(), user.getUsername());

    }

}
