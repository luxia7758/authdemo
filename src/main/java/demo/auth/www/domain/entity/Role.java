package demo.auth.www.domain.entity;

import lombok.Data;

/**
 * role entity
 */
@Data
public class Role {
    /**
     * id
     */
    private String id;
    /**
     * role name
     */
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
