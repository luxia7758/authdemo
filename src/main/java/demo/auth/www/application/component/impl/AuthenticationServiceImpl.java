package demo.auth.www.application.component.impl;

import demo.auth.www.application.dto.AuthToken;
import demo.auth.www.application.component.AuthenticationService;
import demo.auth.www.application.component.TokenRepository;
import demo.auth.www.domain.entity.Role;
import demo.auth.www.domain.entity.User;
import demo.auth.www.domain.service.UserService;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class AuthenticationServiceImpl implements AuthenticationService {

    private TokenRepository tokenRepository;

    private UserService userService;

    private TokenGenerator tokenGenerator;

    private Duration tokenValidDuration;

    public AuthenticationServiceImpl(TokenRepository tokenRepository, UserService userService, TokenGenerator tokenGenerator, Duration tokenValidDuration) {
        this.tokenRepository = tokenRepository;
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.tokenValidDuration = tokenValidDuration;
    }

    public AuthenticationServiceImpl(TokenRepository tokenRepository, UserService userService) {
        /**
         * pre-configured time (2h)
         */
        this(tokenRepository, userService, () -> UUID.randomUUID().toString(), Duration.ofHours(2L));
    }

    @Override
    public AuthToken authenticate(String username, String password) {

        User storedUser = userService.findByUsername(username, password);

        AuthToken token = tokenRepository.findByUserid(storedUser.getId());
        if (null == token) {
            Instant now = Instant.now();
            token = new AuthToken(tokenGenerator.generate(), storedUser, now, now.plus(tokenValidDuration));
            tokenRepository.save(token);
        }

        return token;
    }


    @Override
    public void invalidate(AuthToken token) {
        invalidate(token.getValue());
    }

    @Override
    public void invalidate(String tokenKey) {
        tokenRepository.deleteByKey(tokenKey);
    }

    @Override
    public boolean checkRole(AuthToken token, Role role) {
        return checkRole(token.getValue(),role.getName());

    }
    @Override
    public boolean checkRole(String tokenKey, String roleName) {
        AuthToken storedToken = tokenRepository.findByKey(tokenKey);
        return storedToken.getUser().getRoles()
                .stream()
                .map(Role::getName)
                .anyMatch(n -> n.equals(roleName));

    }

    @Override
    public Set<Role> getRoles(AuthToken token) {
        AuthToken storedToken = tokenRepository.findByKey(token.getValue());
        return storedToken.getUser().getRoles();
    }

    @FunctionalInterface
    public interface TokenGenerator {
        String generate();
    }
}
