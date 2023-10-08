package avenga.poc.testcontainer.redpanda;

import avenga.poc.testcontainer.entity.CustomerEntity;
import avenga.poc.testcontainer.repository.CustomerRepository;
import avenga.poc.testcontainer.service.CustomerService;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// The same database is used for the same class
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContainerApplicationTest extends AbstractIntegrationTest {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerService customerService;

//	@BeforeEach
//	void setUp() {
//		customerRepository.deleteAll();
//
//	}

	@Test
	void contextLoads() {
	}

	@Test
	public void healthy() {
		given(requestSpecification)
				.when()
				.get("/actuator/health")
				.then()
				.statusCode(200)
				.log().ifValidationFails(LogDetail.ALL);
	}



	@Test
	@Order(1)
	public void allCustomers() {
		List<CustomerEntity> customers = List.of(
				new CustomerEntity(null, "Roman", "roman@mail.com"),
				new CustomerEntity(null, "Oleg", "oleg@mail.com")
		);
		customerRepository.saveAll(customers);

		given(requestSpecification)
				.when()
				.get("/api/v1/customers")
				.then()
				.statusCode(200)
				.body(".", hasSize(2+3));
	}
	@Test
	@Order(2)
	public void findAllCustomers() {

		List<CustomerEntity> all = customerService.findAll();
		assertThat(all).hasSize(3);
	}

	@Test
	public void testUnknownUser() {
		String unknownUser = "999";

		given(requestSpecification)
				.when()
				.get("/api/v1/customers/"+ unknownUser)
				.then()
				.statusCode(404);
	}



}
