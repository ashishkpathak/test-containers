package au.id.pathak.testcontainers;

import java.util.List;

import au.id.pathak.testcontainers.db.Artist;
import au.id.pathak.testcontainers.db.ArtistDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

@SpringBootTest(properties = {"spring.datasource.url=jdbc:tc:postgresql:9.6.8:///gomo"})
public class TestContainersSpringSupportTests {

  @Autowired
  private ArtistDao artistDao;


  @Test
  public void test_all_artists_exists() {
    List<Artist> all = artistDao.findAll();
    Assert.isTrue(all.size() == 6, "Should have size of 6.");

    all.forEach(System.out::println);
  }

  @Test
  public void test_van_gogh_exists() {
    List<Artist> all = artistDao.findAll();
    boolean vanGogh = false;
    long van_gogh = all.stream().filter(artist -> containsIgnoreCase(artist.getLastName(), "van gogh")).count();
    Assert.isTrue(van_gogh == 1,"Should have single element.");
  }
}
