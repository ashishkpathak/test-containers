package au.id.pathak.testcontainers.redis;

import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

/**
 *
 *
 */
@Component
public class RedisStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

  @Override
  public void onMessage(MapRecord<String, String, String> message) {
    System.out.println(message.getValue().toString());
  }
}
