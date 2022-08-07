package demo.auth.www.domain.repository;


import demo.auth.www.domain.entity.Role;

public interface RoleRepository {
    Role save(Role entity);
    void delete(Role entity);
    Role findByName(String name);
}
