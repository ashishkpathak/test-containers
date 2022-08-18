package au.id.pathak.testcontainers.redis;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

@TestConfiguration
public class TraditionalRedisTestConfiguation {

  private RedisServer redisServer;

  @Value("${redis.port}")
  public int redisPort = 5555;

  public TraditionalRedisTestConfiguation() {
    this.redisServer = new RedisServer(redisPort);
  }

  @PostConstruct
  public void postConstruct() {
    redisServer.start();
  }

  @PreDestroy
  public void preDestroy() {
    redisServer.stop();
  }

}
