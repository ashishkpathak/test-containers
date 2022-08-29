package au.id.pathak.testcontainers;

import java.util.List;

import au.id.pathak.testcontainers.db.Artist;
import au.id.pathak.testcontainers.db.ArtistDao;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

@SpringBootTest(properties = {"spring.datasource.url=jdbc:tc:postgresql:latest:///gomo"})
@Testcontainers
public class TestContainersSpringSupportTests {

  @Autowired
  private ArtistDao artistDao;


//  @Test
//  public void test_all_artists_exists() {
//    List<Artist> all = artistDao.findAll();
//    Assert.assertTrue(all.size() == 6);
//    all.forEach(System.out::println);
//  }
//
//  @Test
//  public void test_van_gogh_exists() {
//    List<Artist> all = artistDao.findAll();
//    boolean vanGogh = false;
//    long van_gogh = all.stream().filter(artist -> containsIgnoreCase(artist.getLastName(), "van gogh")).count();
//    Assert.assertTrue(van_gogh == 1);
//  }
}
