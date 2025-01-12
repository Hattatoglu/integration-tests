package xmpl.eyaz.integration.redis.service;

import xmpl.eyaz.integration.redis.command.CreateUserCommand;
import xmpl.eyaz.integration.redis.command.FindUserCommand;

public interface UserService {
    CreateUserCommand createUser(CreateUserCommand command);
    FindUserCommand findUser(FindUserCommand command);
}
