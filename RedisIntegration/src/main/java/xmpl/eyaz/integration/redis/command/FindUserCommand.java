package xmpl.eyaz.integration.redis.command;

import xmpl.eyaz.integration.redis.model.User;

import java.util.UUID;

public class FindUserCommand {

    private UUID userId;
    private User user;

    public FindUserCommand() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
