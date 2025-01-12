package xmpl.eyaz.integration.redis.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import xmpl.eyaz.integration.redis.command.CreateUserCommand;
import xmpl.eyaz.integration.redis.command.FindUserCommand;
import xmpl.eyaz.integration.redis.model.User;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

public class RedisCacheTestSupport {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @AfterEach
    void tearDown() {
        if (redisTemplate != null) {
            redisTemplate.getConnectionFactory().getConnection().flushAll();
        }
    }

    protected CreateUserCommand getCreateUserCommand(String userId) {
        CreateUserCommand command = new CreateUserCommand(TestConstant.USERNAME, TestConstant.NAME);

        User user = getUser(TestScenario.SCENARIO_SUCCESS, command.getUsername(), command.getName());
        command.setUser(user);

        return command;
    }

    protected FindUserCommand getFindUserCommand(String userId) {
        FindUserCommand command = new FindUserCommand();
        command.setUserId(UUID.fromString(userId));
        return command;
    }

    protected User getCache(String userId) {
        String value = (String) redisTemplate.opsForValue().get(userId);
        return getUserFromJson(value);
    }


    protected void initiateRedisForFindUserCommand() {
        User one = getUser(TestConstant.USERID_ONE, TestConstant.USERNAME_ONE, TestConstant.NAME_ONE);
        redisTemplate.opsForValue().set(TestConstant.USERID_ONE, getJsonFromUser(one));

        User two = getUser(TestConstant.USERID_TWO, TestConstant.USERNAME_TWO,TestConstant.NAME_TWO);
        redisTemplate.opsForValue().set(TestConstant.USERID_TWO, getJsonFromUser(two));

        User three = getUser(TestConstant.USERID_THREE, TestConstant.USERNAME_THREE, TestConstant.NAME_THREE);
        redisTemplate.opsForValue().set(TestConstant.USERID_THREE, getJsonFromUser(three));
    }

    private User getUserFromJson(String value) {
        try {
            return mapper.readValue(value, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getJsonFromUser(User user) {
        try {
            return mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUser(String userId, String username, String name) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setCreationTime(ZonedDateTime.now(ZoneOffset.UTC));
        user.setUserId(UUID.fromString(userId));
        return user;
    }

}
