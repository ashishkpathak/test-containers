package au.id.pathak.testcontainers.redis;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class RedisService {

  private TCRedisRepository tcRedisRepository;


  public void store(String key, String value){
    System.out.println(key+" : "+value);
    tcRedisRepository.save(key);
  }

  public Optional<String> get(String key){
//    return Optional.ofNullable((String)redisTemplate.opsForValue().get(key));
    return tcRedisRepository.findById(key);
  }

}
