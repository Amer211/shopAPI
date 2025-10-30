package com.example.customer_service.service;

import com.example.customer_service.entity.Customer;
import com.example.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;


    public Customer saveCustomer(Customer customer){
        log.info("Saving new Customer {}: :",customer.getUsername());
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        log.info("fetching all customers");
        return customerRepository.findAll();
    }


    public Customer getCustomerById(Long id) {
        log.info("fetching customer by id: {}", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("customer not found with id:"+ id));
    }
}
