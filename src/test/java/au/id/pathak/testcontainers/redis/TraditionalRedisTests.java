package au.id.pathak.testcontainers.redis;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class TraditionalRedisTests {


  @Test
  public void testRedis() {
    OpenAPI p = new OpenAPIV3Parser().read("");


    System.out.println("Hello");
  }
}
