package org.sadtech.example.jwt.server.service;

import lombok.NonNull;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.sadtech.example.jwt.server.domain.Role;
import org.sadtech.example.jwt.server.domain.User;

@Service
public class UserService {
    private final List<User> users;

    public UserService() {
        this.users = List.of(
                new User("anton", "1234", "Антон", "Иванов", Collections.singleton(Role.USER)),
                new User("ivan", "12345", "Сергей", "Петров", Collections.singleton(Role.ADMIN))
        );
    }

    public Optional<User> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }
}
