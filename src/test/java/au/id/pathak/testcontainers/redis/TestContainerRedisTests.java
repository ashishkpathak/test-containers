package au.id.pathak.testcontainers.redis;

import org.junit.Assert;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class TestContainerRedisTests {


  @Test
  public void run(){
    GenericContainer<?> redis = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine")).withExposedPorts(6379);
    redis.start();
    Integer mappedPort = redis.getMappedPort(6379);
    String host = redis.getHost();
    Assert.assertNotNull(mappedPort);
    Assert.assertNotNull(host);

  }

  public void start(){

  }
}
