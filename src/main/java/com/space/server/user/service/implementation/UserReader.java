package com.space.server.user.service.implementation;

import com.space.server.user.domain.Users;
import com.space.server.user.domain.repository.UserRepository;
import com.space.server.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public Users findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
