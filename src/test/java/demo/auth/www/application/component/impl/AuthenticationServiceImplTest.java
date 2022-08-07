package demo.auth.www.application.component.impl;

import demo.auth.www.application.component.AuthenticationService;
import demo.auth.www.application.component.TokenRepository;
import demo.auth.www.application.dto.AuthToken;
import demo.auth.www.domain.entity.User;
import demo.auth.www.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class AuthenticationServiceImplTest {

    private AuthenticationService test;

    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        test = new AuthenticationServiceImpl(tokenRepository,userService);
    }

    @Test
    void authenticate() {

        User testUser = new User("test","pwd");
        when(userService.findByUsername("test","pwd")).thenReturn(testUser);

        when(tokenRepository.findByUserid("test")).thenReturn(null);

        Instant now = Instant.now();
        AuthToken testToken = new AuthToken("123456",testUser, now,now.plus(Duration.ofMillis(5L)));
        when(tokenRepository.save(any(AuthToken.class))).thenReturn(testToken);

        assertTrue(test.authenticate("test", "pwd").getValue().length() > 30);

    }

    @Test
    void invalidate() {

        User testUser = new User("test","pwd");
        when(userService.findByUsername("test","pwd")).thenReturn(testUser);

        when(tokenRepository.findByUserid("test")).thenReturn(null);

        Instant now = Instant.now();
        AuthToken testToken = new AuthToken("123456",testUser, now,now.plus(Duration.ofMillis(5L)));
        when(tokenRepository.save(any(AuthToken.class))).thenReturn(testToken);

        String key = test.authenticate("test", "pwd").getValue();

        test.invalidate(key);
        doNothing().when(tokenRepository).deleteByKey(key);

    }

    @Test
    void testInvalidate() {
    }

    @Test
    void checkRole() {
    }

    @Test
    void testCheckRole() {
    }

    @Test
    void getRoles() {
    }
}