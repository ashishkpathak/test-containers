package au.id.pathak.testcontainers.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest

public class TraditionalRedisTests {

  @Autowired
  private RedisTemplate template;

  @Test
  public void testRedis() {
    SwaggerParseResult result = new OpenAPIParser().readLocation("https://petstore3.swagger.io/api/v3/openapi.json", null, null);

    // or from a file
    // SwaggerParseResult result = new OpenAPIParser().readLocation("./path/to/openapi.yaml", null, null);

    // the parsed POJO
    OpenAPI openAPI = result.getOpenAPI();
    Set<String> keySet = openAPI.getPaths().keySet();
    for (String s : keySet) {
      System.out.println(s);
      PathItem pathItem = openAPI.getPaths().get(s);
      Operation post = pathItem.getPost();
      if (post!=null){
        RequestBody requestBody = post.getRequestBody();
        Content content = requestBody.getContent();
        Set<String> strings = content.keySet();
        for (String contentKey : strings) {
          MediaType mediaType = content.get(contentKey);
          Schema schema = mediaType.getSchema();
          Map properties = schema.getProperties();
          if(properties!=null)
          properties.forEach((x,y)->{System.out.println(x+" " + y);});
        }
        System.out.println(content);
      }
      List<Parameter> parameters = pathItem.getParameters();
      if (parameters != null)
        for (Parameter parameter : parameters) {
          System.out.println(parameter);
        }
    }
    openAPI.getPaths().forEach((y, x) -> {
      System.out.println(x + " " + y);
    });

  }
}
