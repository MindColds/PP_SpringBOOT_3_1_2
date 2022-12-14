package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByName(String name);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(int id);
}
