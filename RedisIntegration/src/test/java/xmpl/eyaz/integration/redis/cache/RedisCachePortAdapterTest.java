package xmpl.eyaz.integration.redis.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xmpl.eyaz.integration.redis.command.CreateUserCommand;
import xmpl.eyaz.integration.redis.command.FindUserCommand;
import xmpl.eyaz.integration.redis.config.IT;
import xmpl.eyaz.integration.redis.model.User;
import xmpl.eyaz.integration.redis.support.RedisCacheTestSupport;
import xmpl.eyaz.integration.redis.support.TestConstant;
import xmpl.eyaz.integration.redis.support.TestScenario;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@IT
class RedisCachePortAdapterTest extends RedisCacheTestSupport {

    @Autowired
    private RedisCachePort redisCachePort;

    @Test
    void should_saveUser_whenCreateUserCommandPublished() {

        //given
        CreateUserCommand command = getCreateUserCommand(TestScenario.SCENARIO_SUCCESS);

        //when
        CreateUserCommand answer = redisCachePort.save(command);

        //Then
        User cache = getCache(TestScenario.SCENARIO_SUCCESS);

        assertThat(cache).isNotNull();
        assertThat(cache.getUserId()).isNotNull().isEqualTo(answer.getUser().getUserId());
        assertThat(cache.getName()).isNotNull().isEqualTo(answer.getUser().getName());
        assertThat(cache.getUsername()).isNotNull().isEqualTo(answer.getUser().getUsername());
        assertThat(cache.getCreationTime()).isNotNull().isEqualTo(answer.getUser().getCreationTime());

    }

    @Test
    void should_findUser_whenFindUserCommandPublishedWithProperUserId() {
        //initialize
        initiateRedisForFindUserCommand();

        // Given
        FindUserCommand command = getFindUserCommand(TestConstant.USERID_TWO);

        // when
        FindUserCommand answer = redisCachePort.find(command);

        // then
        assertThat(answer.getUser()).isNotNull();
        assertThat(answer.getUser().getUserId()).isNotNull().isEqualTo(UUID.fromString(TestConstant.USERID_TWO));
        assertThat(answer.getUser().getUsername()).isNotNull().isEqualTo(TestConstant.USERNAME_TWO);
        assertThat(answer.getUser().getName()).isNotNull().isEqualTo(TestConstant.NAME_TWO);
    }

    @Test
    void should_returnEmptyUser_whenFindUserCommandPublishedWithUnknownUserId() {
        //initialize
        initiateRedisForFindUserCommand();

        //given
        FindUserCommand command = getFindUserCommand(TestScenario.SCENARIO_UNKNOWN_USER);

        //when
        FindUserCommand answer = redisCachePort.find(command);

        //then
        assertThat(answer.getUser()).isNull();
    }

}