package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserRoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class DefaultUser {
    private final UserService userService;

    private final UserRoleService userRoleService;

    @Autowired
    public DefaultUser(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @PostConstruct
    private void init() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        userRoleService.saveRole(roleAdmin);
        userRoleService.saveRole(roleUser);
        User admin = new User();
        admin.setName("Zaur");
        admin.setPatronim("Albertovich");
        admin.setSurname("Tregulov");
        admin.setAge(34);
        admin.setEmail("zaur@abc.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.addRole(roleAdmin);
        userService.createUser(admin);

    }



}

