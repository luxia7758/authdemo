package demo.auth.www.application.dto;

import demo.auth.www.domain.entity.User;
import lombok.Data;

import java.time.Instant;

@Data
public class AuthToken {

    /**
     * token value
     */
    private String value;
    /**
     * related user
     */
    private User user;

    /**
     * issue at time
     */
    private Instant issueAt;

    /**
     * expire at time
     */
    private Instant expireAt;

    public AuthToken(String value, User user, Instant issueAt, Instant expireAt) {
        this.value = value;
        this.user = user;
        this.issueAt = issueAt;
        this.expireAt = expireAt;
    }
}
