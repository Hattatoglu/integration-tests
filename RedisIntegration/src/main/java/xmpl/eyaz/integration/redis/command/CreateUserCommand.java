package xmpl.eyaz.integration.redis.command;

import xmpl.eyaz.integration.redis.model.User;

public class CreateUserCommand {

    private final String username;
    private final String name;

    private User user;

    public CreateUserCommand(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
