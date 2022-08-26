package au.id.pathak.testcontainers.controller;

import java.util.List;

import au.id.pathak.testcontainers.db.Artist;
import au.id.pathak.testcontainers.db.ArtistDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

  private static final Logger LOG = LoggerFactory.getLogger(ArtistController.class);

  @Autowired
  private ArtistDao artistDao;

  @GetMapping(value = "/api/artist")
  public List<Artist> getArtists() {
    List<Artist> artists = artistDao.findAll();
    artists.forEach(c -> LOG.info("Found an artist: {}", c));
    return artists;
  }
}
