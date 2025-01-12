package xmpl.eyaz.integration.redis.service;

import org.springframework.stereotype.Service;
import xmpl.eyaz.integration.redis.cache.RedisCachePort;
import xmpl.eyaz.integration.redis.command.CreateUserCommand;
import xmpl.eyaz.integration.redis.command.FindUserCommand;
import xmpl.eyaz.integration.redis.model.User;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final RedisCachePort redisCachePort;

    public UserServiceImpl(RedisCachePort redisCachePort) {
        this.redisCachePort = redisCachePort;
    }

    @Override
    public CreateUserCommand createUser(CreateUserCommand command) {
        User user = new User();
        user.setUsername(command.getUsername());
        user.setName(command.getName());
        user.setUserId(UUID.randomUUID());
        user.setCreationTime(ZonedDateTime.now(ZoneOffset.UTC));

        command.setUser(user);
        return redisCachePort.save(command);
    }

    @Override
    public FindUserCommand findUser(FindUserCommand command) {
        return redisCachePort.find(command);
    }
}
