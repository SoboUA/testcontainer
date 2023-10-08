package avenga.poc.testcontainer.redpanda;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.redpanda.RedpandaContainer;
import org.testcontainers.utility.MountableFile;

import java.io.IOException;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {

        }
)
public abstract class AbstractIntegrationTest {

    static Network network = Network.newNetwork();

    static GenericContainer<?> redis = new GenericContainer<>("redis:6-alpine")
            .withExposedPorts(6379).withNetwork(network).withNetworkAliases("redis");

//    static PostgreSQLContainer<?> postgreSQLContainer =
//            new PostgreSQLContainer<>("postgres:14-alpine")
//                    .withCopyFileToContainer(MountableFile.forClasspathResource("schema_deprecated.sql"), "/docker-entrypoint-initdb.d/");

    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:5.7.34");

    static RedpandaContainer kafka = new RedpandaContainer("docker.redpanda.com/vectorized/redpanda:v22.2.1");

    @DynamicPropertySource
    public static void setupThings(DynamicPropertyRegistry registry) throws IOException {
        Startables.deepStart(redis, kafka, mySQLContainer).join();

        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);

        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", redis::getFirstMappedPort);

        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    protected RequestSpecification requestSpecification;

    @LocalServerPort
    protected int localServerPort;

    @BeforeEach
    public void setUpAbstractIntegrationTest() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification = new RequestSpecBuilder()
                .setPort(localServerPort)
                .addHeader(
                        HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .build();
    }


}
