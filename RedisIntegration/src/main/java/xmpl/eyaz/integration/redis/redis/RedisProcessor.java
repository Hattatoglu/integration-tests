package xmpl.eyaz.integration.redis.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisProcessor implements RedisProcess{

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisProcessor(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String findByKey(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}
