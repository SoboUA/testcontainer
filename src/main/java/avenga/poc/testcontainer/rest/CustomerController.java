package avenga.poc.testcontainer.rest;


import avenga.poc.testcontainer.entity.CustomerEntity;
import avenga.poc.testcontainer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerEntity findCustomer(@PathVariable String uid) {
        return customerService.findByUid(uid);
    }

    @GetMapping
    public List<CustomerEntity> findCustomers() {
        return customerService.findAll();
    }
}
