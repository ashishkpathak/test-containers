package au.id.pathak.testcontainers.db;

public class Artist {

  private long id;
  private String firstName, lastName;

  public Artist() {
  }

  public Artist(long id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format(
        "Artist[id=%d, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  // getters & setters omitted for brevity
}
