package com.space.server.user.service;

import com.space.server.user.domain.User;
import com.space.server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandUserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public void signUp(User user) throws Exception {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (userRepository.findByNickname(user.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }
}
