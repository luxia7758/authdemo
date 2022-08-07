package demo.auth.www.application.component;


import demo.auth.www.application.dto.AuthToken;

public interface TokenRepository {
    AuthToken save(AuthToken token);

    AuthToken findByKey(String tokenKey);

    AuthToken findByUserid(String id);

    void deleteByKey(String tokenKey);
}
