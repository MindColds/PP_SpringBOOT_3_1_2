package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface UserRoleDAO {
    Set<Role> getAllRoles();

    Set<Role> getRoleByName(String name);

    void saveUserRole(Role role);
}
