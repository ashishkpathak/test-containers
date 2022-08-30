
<p align="center">
  <br />
  <br />
  <a href="https://github.com/ashishkpathak/test-containers">
    <img src="TestContainers.png" alt="Test Container" width="400">
  </a>
</p>
<!-- Badges -->
<p align="center">
  <!-- CI -->
  <a href="https://ci.appveyor.com/api/projects/status/5n0s6lbigi8wji96/branch/main?svg=true">
    <img src="https://ci.appveyor.com/api/projects/status/5n0s6lbigi8wji96/branch/main?svg=true">
  </a>

  <!-- Github version -->

  <!-- <a href="releases">
    <img src="https://img.shields.io/github/v/release/guardsquare/proguard">
  </a> -->

  <!-- License -->
  <a href="LICENSE">
    <img src="https://img.shields.io/github/license/guardsquare/proguard">
  </a>

</p>

### Integration testing


### Introduction
In this article, I will describe how Prepaid tribe is using test containers for integration testing.  TestContainers.org is a JVM library that allows users to run and manage Docker images and control them from Java code. It provides lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container. Testcontainers make the following kinds of external components testing easier. External components can be one of:

- Database servers - for example, run real PostgreSQL/MySQL as a Docker image,
- Mocked HTTP server - you can mimic the behavior of other HTTP services by using Docker images from MockServer or WireMock,
- Caches - Redis - run real Redis as a Docker image,
- Messaging- streams or queues (like RabbitMQ and others),
- Cloud Managed services- AWS components like S3, Kinesis, DynamoDB, and others, which you can emulate with Localstack
- other application that can be run as a Docker image.


### Scope
Additionaly benefit of having test containers is you can add them as dependencies and start then with docker, which is still better than docker-compose.
To implement an integration test, you need to run your application similarly to a unit test (method annotated by @Test).


First thing would be to include the test container dependencies.

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.8.1</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>testcontainers</artifactId>
    <version>1.17.3</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>1.17.3</version>
    <scope>test</scope>
</dependency>

```
In this example I'm using Postgres, but you can use any database as per your requirements. The following postgres driver dependency is added.

```xml
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.4.2</version>
    </dependency>
```

#### Test updates

I'm using springboot application, @SpringBootTest would load up the application context.  Jupiter (JUnit 5) integration is provided by means of the @Testcontainers annotation. The extension finds all fields that are annotated with @Container and calls their container lifecycle methods (methods on the Startable interface). Containers declared as static fields will be shared between test methods. They will be started only once before any test method is executed and stopped after the last test method has executed. Containers declared as instance fields will be started and stopped for every test method.

```java
@SpringBootTest()
@Testcontainers
public class ApplicationIntegrationTests {


    @Containers
    private static PostgresSQLContainer container = new PostgresSQLContainer();

    @Autowired
    private ArtistDao artists;

    @Test
    public void test_all_artists_exists(){

            List<Artists> arts = artists.findAll();    
            
    }
}
    
```
While running integration testing, we are required to provide the hostname and port of the test database. This is generally configured as spring config


```properties
spring.datasource.username=db_username
spring.datasource.password=db_password
spring.datasource.database=db_name
spring.datasource.url=jdbc:postgresql://localhost:port

```
However the container can start on any random port on the host, so we need means to provide database name, username, password , host and port dynamically. Spring provides means to do this using @DynamicPropertySource annotation.

```java

  @DynamicPropertySource
  public static void overrideProperties(DynamicPropertyRegistry registry){
    registry.add("spring.datasource.username", container::getUsername);
    registry.add("spring.datasource.password", container::getPassword);
    registry.add("spring.datasource.database", container::getDatabaseName);
    registry.add("spring.datasource.url", container::getJdbcUrl);
  }
```














Note: This extension has only been tested with sequential test execution. Using it with parallel test execution is unsupported and may have unintended side effects.
