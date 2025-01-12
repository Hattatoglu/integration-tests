package xmpl.eyaz.integration.redis.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;
import xmpl.eyaz.integration.redis.command.CreateUserCommand;
import xmpl.eyaz.integration.redis.command.FindUserCommand;
import xmpl.eyaz.integration.redis.model.User;
import xmpl.eyaz.integration.redis.redis.RedisProcess;

import java.util.Objects;


@Component
public class RedisCachePortAdapter implements RedisCachePort{

    private final RedisProcess redisProcess;
    private ObjectMapper mapper;

    public RedisCachePortAdapter(RedisProcess redisProcess) {
        this.redisProcess = redisProcess;
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public CreateUserCommand save(CreateUserCommand command) {
        redisProcess.save(command.getUser().getUserId().toString(), getJsonFromUser(command.getUser()));
        return command;
    }

    @Override
    public FindUserCommand find(FindUserCommand command) {
        String value = redisProcess.findByKey(command.getUserId().toString());
        if (!Objects.isNull(value)) {
            command.setUser(getUserFromJson(value));
        }
        return command;
    }

    private String getJsonFromUser(User user) {
        try {
            return mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUserFromJson(String value) {
        try {
            return mapper.readValue(value, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
