package au.id.pathak.testcontainers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class TestContainersConfiguration {

  @Value("${redis.port}")
  private int redisPort;


  @Value("${redis.host}")
  private String redisHost;

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration);
//    jedisConnectionFactory.afterPropertiesSet();
    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    StringRedisSerializer keySerializer = new StringRedisSerializer();
    StringRedisSerializer valueSerializer = new StringRedisSerializer();

    RedisSerializationContext.RedisSerializationContextBuilder<Object, String> builder = RedisSerializationContext.newSerializationContext(keySerializer);
    RedisSerializationContext<Object, String> context = builder.value((RedisSerializer<String>) valueSerializer).build();

    template.setConnectionFactory(jedisConnectionFactory);
    template.setKeySerializer(keySerializer);
    template.setValueSerializer(valueSerializer);
    template.afterPropertiesSet();
    return template;
  }


}
