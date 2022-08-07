package demo.auth.www.domain.service;


import demo.auth.www.domain.entity.Role;
import demo.auth.www.domain.entity.User;

public interface UserService {
    /**
     * create a user
     * @param entity
     *
     * @return
     */
    User create(User entity);

    /**
     * delete a user
     * @param entity
     */
    void delete(User entity);

    /**
     * add role for input user
     *
     * @param user
     * @param role
     */
    void associateRole(User user, Role role);

    /**
     * find a user with username and raw password
     * @param username
     * @param password
     * @return
     */
    User findByUsername(String username,String password);

}
