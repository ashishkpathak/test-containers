package au.id.pathak.testcontainers;

import java.util.List;

import au.id.pathak.testcontainers.db.Artist;
import au.id.pathak.testcontainers.db.ArtistDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.util.Assert;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

@SpringBootTest
@Testcontainers
class TestContainersApplicationTests {

  @Autowired
  private ArtistDao artistDao;

  @Container
  private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest");


  @DynamicPropertySource
  public static void override(DynamicPropertyRegistry registry){
    registry.add("spring.datasource.username", container::getUsername);
    registry.add("spring.datasource.password", container::getPassword);
    registry.add("spring.datasource.url", container::getJdbcUrl);
  }

  @Test
  public void test_all_artists_exists() {
    List<Artist> all = artistDao.findAll();
    all.forEach(System.out::println);
    Assert.isTrue(all.size() == 6, "Size should be 6");
  }

  @Test
  public void test_van_gogh_exists() {
    List<Artist> all = artistDao.findAll();
    long van_gogh = all.stream().filter(artist -> containsIgnoreCase(artist.getLastName(), "van gogh")).count();
    Assert.isTrue(van_gogh == 1, "Should have single entry.");
  }

}
