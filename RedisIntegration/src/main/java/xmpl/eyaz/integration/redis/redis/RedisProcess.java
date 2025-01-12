package xmpl.eyaz.integration.redis.redis;

public interface RedisProcess {
    void save(String key, String value);
    String findByKey(String key);
}
