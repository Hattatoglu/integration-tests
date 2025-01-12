package xmpl.eyaz.integration.redis.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app-redis-config")
public class RedisConfigData {

    private String host;
    private int port;
    private int database;
    private String password;
    private int timeout;
    private String keySerializer;
    private String hashKeySerializer;
    private String valueSerializer;
    private String hashValueSerializer;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getKeySerializer() {
        return keySerializer;
    }

    public void setKeySerializer(String keySerializer) {
        this.keySerializer = keySerializer;
    }

    public String getHashKeySerializer() {
        return hashKeySerializer;
    }

    public void setHashKeySerializer(String hashKeySerializer) {
        this.hashKeySerializer = hashKeySerializer;
    }

    public String getValueSerializer() {
        return valueSerializer;
    }

    public void setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
    }

    public String getHashValueSerializer() {
        return hashValueSerializer;
    }

    public void setHashValueSerializer(String hashValueSerializer) {
        this.hashValueSerializer = hashValueSerializer;
    }
}
