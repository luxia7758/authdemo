package demo.auth.www.application.component;


import demo.auth.www.application.dto.AuthToken;
import demo.auth.www.domain.entity.Role;

import java.util.Set;

public interface AuthenticationService {

    /**
     * get token by username password
     *
     * @param username
     * @param password
     * @return
     */
    AuthToken authenticate(String username, String password);

    /**
     * invalidate a token
     * @param token
     */
    void invalidate(AuthToken token);

    void invalidate(String tokenKey);

    /**
     * check if the token owner has input role
     * @param token
     * @param role
     * @return
     */
    boolean checkRole(AuthToken token, Role role);


    boolean checkRole(String tokenKey, String roleName);

    Set<Role> getRoles(AuthToken token);
}
