package au.id.pathak.testcontainers.redis;

import java.util.LinkedList;
import java.util.List;

import au.id.pathak.testcontainers.TestContainersApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = { TraditionalRedisTests.class })
public class TraditionalRedisTests {

  @Autowired
  private RedisTemplate redisTemplate;


  @Test
  public void rotateList() {

    LinkedList<Integer> linkedList =new LinkedList<>();
    linkedList.add(1);
    linkedList.add(2);
    linkedList.add(3);
    linkedList.add(4);
    linkedList.add(5);
    int k = 3;
    int size = linkedList.size();
    k =  k % size;
    List<Integer> left = linkedList.subList(0, k);
    List<Integer> right = linkedList.subList(k,size);
    System.out.println(left);
    System.out.println(right);
    int count = 0;
    for(Integer i: left){

      linkedList.set(i,count++);

    }
    for(Integer i: right) {
      linkedList.set(i,count++);
    }
    System.out.println(linkedList);
  }
  @Test
  public void testRedis() {
    ListOperations listOperations = redisTemplate.opsForList();
    RedisOperations operations = listOperations.getOperations();
    System.out.println(operations);
    //    SwaggerParseResult result = new OpenAPIParser().readLocation("https://petstore3.swagger.io/api/v3/openapi.json", null, null);
//
//    // or from a file
//    // SwaggerParseResult result = new OpenAPIParser().readLocation("./path/to/openapi.yaml", null, null);
//
//    // the parsed POJO
//    OpenAPI openAPI = result.getOpenAPI();
//    Set<String> keySet = openAPI.getPaths().keySet();
//    for (String s : keySet) {
//      System.out.println(s);
//      PathItem pathItem = openAPI.getPaths().get(s);
//      Operation post = pathItem.getPost();
//      if (post!=null){
//        RequestBody requestBody = post.getRequestBody();
//        Content content = requestBody.getContent();
//        Set<String> strings = content.keySet();
//        for (String contentKey : strings) {
//          MediaType mediaType = content.get(contentKey);
//          Schema schema = mediaType.getSchema();
//          Map properties = schema.getProperties();
//          if(properties!=null)
//          properties.forEach((x,y)->{System.out.println(x+" " + y);});
//        }
//        System.out.println(content);
//      }
//      List<Parameter> parameters = pathItem.getParameters();
//      if (parameters != null)
//        for (Parameter parameter : parameters) {
//          System.out.println(parameter);
//        }
//    }
//    openAPI.getPaths().forEach((y, x) -> {
//      System.out.println(x + " " + y);
//    });

  }
}
