package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleDAO userRoleDAO;

    @Autowired
    public UserRoleServiceImpl(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Override
    public Set<Role> getAllRoles() {
        return userRoleDAO.getAllRoles();
    }

    @Override
    public Set<Role> getRoleByName(String name) {
        Set<Role> allRoles = new HashSet<>();
        for (Role role : getAllRoles()) {
            if (name.contains(role.getName()))
                allRoles.add(role);
        }
        return allRoles;
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        userRoleDAO.saveUserRole(role);

    }
}
