package demo.auth.www.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * user entity
 */
@Data
public class User {

    /**
     * id
     */
    private String id;
    /**
     * username
     */
    private String name;

    /**
     * password with encrypted form
     */
    private String password;

    /**
     * roles associated with the user
     */
    private Set<Role> roles;


    public User(String name, String password) {
        this.name = name;
        this.id = name;//a simple way
        this.password = password;
        this.roles = new HashSet<>();
    }
}
