package au.id.pathak.testcontainers;

import java.util.List;

import au.id.pathak.testcontainers.db.Artist;
import au.id.pathak.testcontainers.db.ArtistDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

@SpringBootTest
class TestContainersApplicationTests {

  @Autowired
  private ArtistDao artistDao;


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
