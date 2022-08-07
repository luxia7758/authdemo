package demo.auth.www.domain.service.impl;

import demo.auth.www.domain.entity.Role;
import demo.auth.www.domain.entity.User;
import demo.auth.www.domain.repository.RoleRepository;
import demo.auth.www.domain.repository.UserRepository;
import demo.auth.www.domain.service.PasswordEncoder;
import demo.auth.www.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserService test;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        test = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);

    }

    @Test
    void create() {
        User testUser = new User("test","pwd");
        when(passwordEncoder.encrypt("pwd")).thenReturn("pwd123");

        User toSaveUser = new User("test","pwd123");
        when(userRepository.save(eq(toSaveUser))).thenReturn(toSaveUser);

        User toReturnUser = new User("test","pwd123");
        assertEquals(toReturnUser,test.create(testUser));
    }

    @Test
    void delete() {
        User testUser = new User("test","pwd");

        doNothing().when(userRepository).delete(any(User.class));
        test.delete(testUser);

    }

    @Test
    void associateRole() {
        User testUser = new User("test","pwd");
        Role testRole = new Role("testRole");

        test.associateRole(testUser,testRole);

        assertEquals(1,testUser.getRoles().size());
    }

    @Test
    void associateExistRole() {
        User testUser = new User("test","pwd");
        Role testRole = new Role("testRole");
        Role testRole2 = new Role("testRole");


        test.associateRole(testUser,testRole);
        test.associateRole(testUser,testRole);
        test.associateRole(testUser,testRole2);

        assertEquals(1,testUser.getRoles().size());
    }
}