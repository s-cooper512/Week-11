package org.example.customerthingagain.service;

import org.example.customerthingagain.model.Customer;
import org.example.customerthingagain.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer;
    }

}
