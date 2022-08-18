package au.id.pathak.testcontainers;

import java.util.Optional;

import au.id.pathak.testcontainers.redis.RedisService;
import au.id.pathak.testcontainers.redis.TraditionalRedisTests;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = { TraditionalRedisTests.class })
@TestPropertySource(locations = "classpath:application-test.properties")
class TestContainersApplicationTests {

  @Autowired
  private RedisService service;

  @Test
  void testKeyValue() {
    final String key = "Key";
    final String value = "zValue";
    service.store(key, value);
    Optional<String> retValue = service.get(key);
    Assert.assertTrue(retValue.isPresent());
  }

}
