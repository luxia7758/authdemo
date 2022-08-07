package demo.auth.www.infrastructure.repository.impl;

import demo.auth.www.domain.entity.Role;
import demo.auth.www.domain.repository.RoleRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRoleRepository implements RoleRepository {

    private Map<String, Role> roleStorage = new HashMap<>();

    @Override
    public Role save(Role entity) {
        if (roleStorage.containsKey(entity.getName())) {
            throw new IllegalArgumentException("username exists.");
        }
        return roleStorage.put(entity.getName(), entity);
    }

    @Override
    public void delete(Role entity) {
        Role removed = roleStorage.remove(entity.getName());
        if (removed == null) {
            throw new IllegalArgumentException("user not exists.");
        }
    }

    @Override
    public Role findByName(String name) {
        Role role = roleStorage.get(name);
        if(role == null){
            throw new IllegalArgumentException("role not exists.");
        }
        return role;
    }

}
