package demo.auth.www.domain.service.impl;

import demo.auth.www.domain.entity.Role;
import demo.auth.www.domain.entity.User;
import demo.auth.www.domain.repository.RoleRepository;
import demo.auth.www.domain.repository.UserRepository;
import demo.auth.www.domain.service.PasswordEncoder;
import demo.auth.www.domain.service.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User create(User entity) {
        entity.setPassword(passwordEncoder.encrypt(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public void associateRole(User user, Role role) {
        if(user == null){
            throw new IllegalArgumentException("user can not be null");
        }
        if(role == null){
            throw new IllegalArgumentException("role can not be null");
        }
        Role findedRole = roleRepository.findByName(role.getName());

        user.getRoles().add(findedRole);
    }

    @Override
    public User findByUsername(String username,String password) {
        User user = userRepository.findByUsername(username);

        if (!passwordEncoder.check(password, user.getPassword())) {
            throw new IllegalArgumentException("invalid password.");
        }
        return user;

    }
}
