package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserRoleDAOImpl implements UserRoleDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getAllRoles() {
        return entityManager.createQuery("SELECT role from Role role", Role.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Role> getRoleByName(String name) {
        return entityManager.createQuery("SELECT role from Role role where role.name = :name",Role.class)
                .setParameter("name", name)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public void saveUserRole(Role role) {
        entityManager.persist(role);

    }
}
