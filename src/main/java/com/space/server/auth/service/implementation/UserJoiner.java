package com.space.server.auth.service.implementation;

import com.space.server.auth.presentation.dto.request.JoinUserRequest;
import com.space.server.common.exception.ErrorCode;
import com.space.server.common.exception.SpaceException;
import com.space.server.core.inventory.domain.Inventory;
import com.space.server.core.inventory.service.implementation.InventoryCreator;
import com.space.server.core.inventory.service.implementation.InventoryUpdater;
import com.space.server.core.item.domain.Item;
import com.space.server.core.item.service.implementation.ItemReader;
import com.space.server.user.domain.Users;
import com.space.server.user.domain.repository.UserRepository;
import com.space.server.user.domain.value.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserJoiner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final InventoryCreator inventoryCreator;
    private final InventoryUpdater inventoryUpdater;
    private final ItemReader itemReader;

    @Transactional
    public void joinProcess(JoinUserRequest joinUserRequest) {

        String email = joinUserRequest.email();
        String password = joinUserRequest.password();

        Boolean isExist = userRepository.existsByEmail(email);

        if (isExist) {
            throw new SpaceException(ErrorCode.USER_EXISTED);
        }

        Users user = Users.normalUserBuilder()
                .username(joinUserRequest.username())
                .type("normal")
                .email(email)
                .password(passwordEncoder.encode(password))
                .age(joinUserRequest.age())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        Item head = itemReader.findById(1L);
        Item theme = itemReader.findById(2L);

        Inventory In_head = new Inventory(head, user);
        Inventory In_theme = new Inventory(theme, user);

        inventoryCreator.create(In_head);
        inventoryCreator.create(In_theme);

        inventoryUpdater.equip(In_head);
        inventoryUpdater.equip(In_theme);
    }
}
