package au.id.pathak.testcontainers;

import java.util.List;

import au.id.pathak.testcontainers.db.Artist;
import au.id.pathak.testcontainers.db.ArtistDao;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(classes = { ArtistsApplication.class })
@Testcontainers
@ActiveProfiles("test")
class TestContainersApplicationTests {

  @Autowired
  private ArtistDao artistDao;

  @Container
  static PostgreSQLContainer container =
      (PostgreSQLContainer) new PostgreSQLContainer("postgres:latest").withReuse(true);


  @DynamicPropertySource
  public static void overloadedProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url",()->((PostgreSQLContainer)container).getJdbcUrl());
    registry.add("spring.datasource.username",()-> ((PostgreSQLContainer)container).getUsername());
    registry.add("spring.datasource.password", ()-> ((PostgreSQLContainer)container).getPassword());
  }

  @Test
  public void testDatabaseConnections() {
    Integer onYourHost = container.getMappedPort(5432);


    String containerId = container.getContainerId();
    System.out.println(containerId);

    // container.withExposedPorts(15432);
    List<Artist> all = artistDao.findAll();
    Assert.assertTrue(all.size() == 6);

    all.forEach(System.out::println);
  }

}
