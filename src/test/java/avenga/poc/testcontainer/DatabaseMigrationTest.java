package avenga.poc.testcontainer;

import avenga.poc.testcontainer.entity.CustomerEntity;
import avenga.poc.testcontainer.repository.CustomerRepository;
import avenga.poc.testcontainer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
// TODO Sobo Investigation for flyway:clean and :migrate
public class DatabaseMigrationTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Container
    private static MySQLContainer<?> dbContainer = new MySQLContainer<>("mysql:5.7.34")
            .withCopyFileToContainer(MountableFile.forClasspathResource("db/migration/"), "/docker-entrypoint-initdb.d/");



    @DynamicPropertySource
    public static void setupProps(DynamicPropertyRegistry registry) {
//        Startables.deepStart(dbContainer);

        registry.add("spring.datasource.url", dbContainer::getJdbcUrl);
        registry.add("spring.datasource.username", dbContainer::getUsername);
        registry.add("spring.datasource.password", dbContainer::getPassword);
    }

    @Test
    public void findAllCustomers() {

        List<CustomerEntity> all = customerService.findAll();
        assertThat(all).hasSize(3);
    }

}
