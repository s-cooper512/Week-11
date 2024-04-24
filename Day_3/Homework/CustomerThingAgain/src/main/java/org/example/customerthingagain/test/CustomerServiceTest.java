package org.example.customerthingagain.test;

import org.example.customerthingagain.model.Address;
import org.example.customerthingagain.model.Customer;
import org.example.customerthingagain.repository.ICustomerRepository;
import org.example.customerthingagain.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(
                new Customer(1, "John Doe", "john@example.com", 1234, null, new Address()),
                new Customer(2, "Jane Smith", "jane@example.com", 5678, null, new Address())
        );
        when(customerRepository.findAll()).thenReturn(customers);
        assertEquals(customers, customerService.getAllCustomers());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testGetCustomerById() {
        int customerId = 1;
        Customer customer = new Customer(customerId, "John Doe", "john@example.com", 1234, null, new Address());
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        assertEquals(customer, customerService.getCustomerById(customerId));
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer(null, "John Doe", "john@example.com", 1234, null, new Address());
        when(customerRepository.save(customer)).thenReturn(customer);
        assertEquals(customer, customerService.addCustomer(customer));
        verify(customerRepository, times(1)).save(customer);
    }
}

