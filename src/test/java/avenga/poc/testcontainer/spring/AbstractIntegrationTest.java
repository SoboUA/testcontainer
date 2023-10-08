package avenga.poc.testcontainer.spring;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

/**
 *  https://bsideup.github.io/posts/local_development_with_testcontainers/
 *  TODO Sobo Add .withReuse(true) + devtools for faster startup
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public abstract class AbstractIntegrationTest {
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:5.7.34");

        static GenericContainer<?> redis = new GenericContainer<>("redis:3-alpine")
                .withExposedPorts(6379);

        static KafkaContainer kafka = new KafkaContainer();

        public static Map<String, String> getProperties() {
            Startables.deepStart(Stream.of(redis, kafka, mySQLContainer)).join();

            return Map.of(
                    "spring.datasource.url", mySQLContainer.getJdbcUrl(),
                    "spring.datasource.username", mySQLContainer.getUsername(),
                    "spring.datasource.password", mySQLContainer.getPassword(),

                    "spring.redis.host", redis.getContainerIpAddress(),
                    "spring.redis.port", redis.getFirstMappedPort() + "",
                    "spring.kafka.bootstrap-servers", kafka.getBootstrapServers()
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            var env = applicationContext.getEnvironment();
            env.getPropertySources().addFirst(new MapPropertySource(
                    "testcontainers",
                    (Map) getProperties()
            ));
        }
    }
}
