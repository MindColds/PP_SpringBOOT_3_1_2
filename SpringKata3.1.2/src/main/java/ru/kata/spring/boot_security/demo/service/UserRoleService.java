package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface UserRoleService {
    Set<Role> getAllRoles();

    Set<Role> getRoleByName(String name);

    void saveRole(Role role);

}
