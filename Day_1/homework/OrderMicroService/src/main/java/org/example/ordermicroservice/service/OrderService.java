package org.example.ordermicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.ordermicroservice.dto.CustomerDTO;
import org.example.ordermicroservice.model.Order;
import org.example.ordermicroservice.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IOrderRepository orderRepository;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(Order order) {
        Integer customerId = order.getCustomerId();
        //Somehow find a way to check this customer exists
        log.info("Customer wit id " + customerId + " was passed");

        ResponseEntity<CustomerDTO> response = restTemplate.getForEntity(
                "http://localhost:8080/customers/{customerId}",
                CustomerDTO.class,
                customerId);

        if (response.getStatusCode().is2xxSuccessful()) {
            CustomerDTO customerDTO = response.getBody();

            if (customerDTO == null ) {
                log.error("Customer not found");
                return null;
            }

            log.info("Customer was found  with name " + customerDTO.getName());

            return orderRepository.save(order);
        }
        return null;
    }
}
