package avenga.poc.testcontainer;

import avenga.poc.testcontainer.entity.CustomerEntity;
import avenga.poc.testcontainer.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.containers.output.OutputFrame.OutputType.STDERR;
import static org.testcontainers.containers.output.OutputFrame.OutputType.STDOUT;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
//        "spring.datasource.url=jdbc:tc:mysql:5.7.34:///test_container_db"
        })
@Testcontainers
public class ExperimentalContainerTests {

    Logger log = LoggerFactory.getLogger(ExperimentalContainerTests.class);

    @Autowired
    private CustomerService customerService;
    @Container
    private static MySQLContainer<?> dbContainer = (MySQLContainer) new MySQLContainer<>("mysql:5.7.34")
            .withEnv("GREETING", "Hello,world");
//            .withDatabaseName("test_container_db")
//            .withUsername("root")
//            .withPassword("admin");

    @DynamicPropertySource
    public static void setupProps(DynamicPropertyRegistry registry) {
//        Startables.deepStart(dbContainer);

        registry.add("spring.datasource.url", dbContainer::getJdbcUrl);
        registry.add("spring.datasource.username", dbContainer::getUsername);
        registry.add("spring.datasource.password", dbContainer::getPassword);
    }


    @Test
    public void displayLogsAsString() {
        String logs = dbContainer.getLogs();
        String infoLogs = dbContainer.getLogs(STDOUT);
        String errorLogs = dbContainer.getLogs(STDERR);


        assertThat(logs).isNotBlank();
    }

    @Test
    public void displayEnvVariable() {
        Assertions.assertEquals("Hello,world", dbContainer.getEnvMap().get("GREETING"));
    }

    @Test
    public void logsAsStream() {
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log).withSeparateOutputStreams();
        dbContainer.followOutput(logConsumer);
    }
}
