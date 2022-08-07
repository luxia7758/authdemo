package demo.auth.www.infrastructure.component.impl;

import demo.auth.www.application.dto.AuthToken;
import demo.auth.www.application.component.TokenRepository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTokenRepository implements TokenRepository {

    private Map<String, AuthToken> tokenStorage = new HashMap<>();

    @Override
    public AuthToken save(AuthToken token) {

        return tokenStorage.put(token.getValue(), token);
    }

    @Override
    public AuthToken findByKey(String tokenKey) {
        AuthToken authToken = tokenStorage.get(tokenKey);
        if (authToken.getExpireAt().isAfter(Instant.now())) {
            return authToken;
        } else {
            return null;
        }

    }

    @Override
    public AuthToken findByUserid(String id) {
        return tokenStorage.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(e -> e.getUser().getId().equals(id))
                .filter(e -> e.getExpireAt().isAfter(Instant.now()))
                .findAny()
                .orElse(null);


    }

    @Override
    public void deleteByKey(String tokenKey) {
        tokenStorage.remove(tokenKey);
    }
}