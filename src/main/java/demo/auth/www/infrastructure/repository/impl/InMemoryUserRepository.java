package demo.auth.www.infrastructure.repository.impl;

import demo.auth.www.domain.entity.User;
import demo.auth.www.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> userStorage = new HashMap<>();

    @Override
    public User save(User entity) {
        if (userStorage.containsKey(entity.getName())) {
            throw new IllegalArgumentException("username exists.");
        }
        return userStorage.put(entity.getName(), entity);
    }

    @Override
    public void delete(User entity) {
        User removed = userStorage.remove(entity.getName());
        if (removed == null) {
            throw new IllegalArgumentException("user not exists.");
        }
    }

    @Override
    public User findByUsername(String username) {
        User user = userStorage.get(username);
        if(user == null){
            throw new IllegalArgumentException("user not exists.");
        }
        return user;
    }
}
