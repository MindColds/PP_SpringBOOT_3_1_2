package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByName(String name);

    void createUser(User user);

    void updateUser(User user, int id);

    void deleteUser(int id);
}
