package avenga.poc.testcontainer.spring;

import avenga.poc.testcontainer.TestcontainerApplication;
import org.springframework.boot.test.context.TestConfiguration;

/**
 *
 * Spring recommended way to work with containers
 *
 */
@TestConfiguration(proxyBeanMethods = false)
public class ContainerApplicationTest {

    public static void main(String[] args) {
//		SpringApplication.from(TestcontainerApplication::main).with(AbstractIntegrationTest.Initializer.class).run(args);

        var springApplication = TestcontainerApplication.createSpringApplication();
        springApplication.addInitializers(new AbstractIntegrationTest.Initializer());

        springApplication.run(args);
    }

}
