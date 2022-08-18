package au.id.pathak.testcontainers.redis;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

//  @Autowired
//  private TCRedisRepository tcRedisRepository;

  @Autowired
  private RedisTemplate redisTemplate;

  public void store(String key, String value){
    System.out.println(key+" : "+value);

    redisTemplate.opsForValue().set(key,value);
//    tcRedisRepository.save(key);
  }

  public Optional<String> get(String key){
    return Optional.ofNullable((String)redisTemplate.opsForValue().get(key));
//    return repository.findById(key);
  }

}
