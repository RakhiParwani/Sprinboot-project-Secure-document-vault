package net.SkitCollege.Securedocumentvault.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class redisconfigure {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate  redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // KEY = string
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // VALUE = JSON (serialization)
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}