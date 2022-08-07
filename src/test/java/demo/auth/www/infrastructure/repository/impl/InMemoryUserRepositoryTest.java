package demo.auth.www.infrastructure.repository.impl;

import demo.auth.www.domain.entity.User;
import demo.auth.www.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserRepositoryTest {

    private UserRepository test;

    @BeforeEach
    void init() {
        test = new InMemoryUserRepository();
    }

    @Test
    void saveOne() {
        User testUser = new User("test", "pwd");
        test.save(testUser);
    }


    @Test
    void saveExists() {
        User testUser = new User("test", "pwd");
        User testUser2 = new User("test", "pwd2");
        test.save(testUser);
        assertThrows(IllegalArgumentException.class,()->test.save(testUser2));
    }

    @Test
    void delete() {
        User testUser = new User("test", "pwd");
        test.save(testUser);
        test.delete(testUser);
    }

    @Test
    void deleteNotExists() {
        User testUser = new User("test", "pwd");
        assertThrows(IllegalArgumentException.class,()->test.delete(testUser));
    }


    @Test
    void findByUsername() {
        User testUser = new User("test", "pwd");
        test.save(testUser);

        User exceptUser = new User("test", "pwd");
        assertEquals(exceptUser,test.findByUsername("test"));

        User testUser2 = new User("test2", "pwd2");
        test.save(testUser2);

        User exceptUser2 = new User("test2", "pwd2");
        assertEquals(exceptUser2,test.findByUsername("test2"));

        test.delete(testUser);

        assertThrows(IllegalArgumentException.class,()->test.findByUsername("test"));

    }

}