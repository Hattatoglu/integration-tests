package xmpl.eyaz.integration.redis.cache;

import xmpl.eyaz.integration.redis.command.CreateUserCommand;
import xmpl.eyaz.integration.redis.command.FindUserCommand;

public interface RedisCachePort {
    CreateUserCommand save(CreateUserCommand command);
    FindUserCommand find(FindUserCommand command);
}
