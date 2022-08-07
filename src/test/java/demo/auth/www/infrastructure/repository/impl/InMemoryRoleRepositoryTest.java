package demo.auth.www.infrastructure.repository.impl;

import demo.auth.www.domain.entity.Role;
import demo.auth.www.domain.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InMemoryRoleRepositoryTest {

    private RoleRepository test;

    @BeforeEach
    void init() {
        test = new InMemoryRoleRepository();
    }

    @Test
    void saveOne() {
        Role testRole = new Role("test");
        test.save(testRole);
    }


    @Test
    void saveExists() {
        Role testRole = new Role("test");
        Role testRole2 = new Role("test");
        test.save(testRole);
        assertThrows(IllegalArgumentException.class,()->test.save(testRole2));
    }

    @Test
    void delete() {
        Role testRole = new Role("test");
        test.save(testRole);
        test.delete(testRole);
    }

    @Test
    void deleteNotExists() {
        Role testRole = new Role("test");
        assertThrows(IllegalArgumentException.class,()->test.delete(testRole));
    }


}