package au.id.pathak.testcontainers.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TCRedisRepository extends CrudRepository<String, String> {

}