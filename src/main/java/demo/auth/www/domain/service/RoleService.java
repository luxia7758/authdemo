package demo.auth.www.domain.service;


import demo.auth.www.domain.entity.Role;

public interface RoleService {

    /**
     * create a role
     * @param entity
     * @return
     */
    Role create(Role entity);

    /**
     * delete a role
     * @param entity
     */
    void delete(Role entity);

}
