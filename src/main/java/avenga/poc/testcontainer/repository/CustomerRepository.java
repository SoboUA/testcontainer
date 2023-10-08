package avenga.poc.testcontainer.repository;

import avenga.poc.testcontainer.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {


}
