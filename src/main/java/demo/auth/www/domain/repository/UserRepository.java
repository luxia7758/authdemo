package demo.auth.www.domain.repository;


import demo.auth.www.domain.entity.User;

public interface UserRepository {
    User save(User entity);
    void delete(User entity);
    User findByUsername(String username);
}
