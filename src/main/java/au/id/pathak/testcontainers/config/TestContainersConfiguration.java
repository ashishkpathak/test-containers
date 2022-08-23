package au.id.pathak.testcontainers.config;

import org.springframework.stereotype.Component;

@Component
public class TestContainersConfiguration {

//  @Value("${redis.port}")
//  private int redisPort;
//
//
//  @Value("${redis.host}")
//  private String redisHost;
//
//  @Bean
//  public JedisConnectionFactory jedisConnectionFactory() {
//    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);
//    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration);
////    jedisConnectionFactory.afterPropertiesSet();
//    return jedisConnectionFactory;
//  }
//
//  @Bean
//  public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//    RedisTemplate<String, Object> template = new RedisTemplate<>();
//    StringRedisSerializer keySerializer = new StringRedisSerializer();
//    StringRedisSerializer valueSerializer = new StringRedisSerializer();
//
//    RedisSerializationContext.RedisSerializationContextBuilder<Object, String> builder = RedisSerializationContext.newSerializationContext(keySerializer);
//    RedisSerializationContext<Object, String> context = builder.value((RedisSerializer<String>) valueSerializer).build();
//
//    template.setConnectionFactory(jedisConnectionFactory);
//    template.setKeySerializer(keySerializer);
//    template.setValueSerializer(valueSerializer);
//    template.afterPropertiesSet();
//    return template;
//  }


}
