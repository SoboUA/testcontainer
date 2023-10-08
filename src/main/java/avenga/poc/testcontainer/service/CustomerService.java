package avenga.poc.testcontainer.service;

import avenga.poc.testcontainer.entity.CustomerEntity;
import avenga.poc.testcontainer.exception.ResourceNotFoundException;
import avenga.poc.testcontainer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerEntity findByUid(String uid) {
        return customerRepository.findById(uid).orElseThrow(ResourceNotFoundException::new);

    }

    public List<CustomerEntity> findAll() {
        return (List<CustomerEntity>) customerRepository.findAll();
    }
}
