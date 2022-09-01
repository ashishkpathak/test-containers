package au.id.pathak.testcontainers.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ArtistDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Artist> findAll(){
    return jdbcTemplate.query(
        "SELECT id, first_name, last_name FROM artist",
        (rs, rowNum) -> new Artist(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")));


  }
}
