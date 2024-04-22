package org.example.customermicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerOrderController {

    @Autowired
    private RestTemplate restTemplate; // Assuming RestTemplate is configured

    @GetMapping("/customers/{id}/orders")
    public ResponseEntity<String> getAllOrdersByCustomer(@PathVariable Long id) {
        String orderServiceUrl = "LOCALHOST:8081/orders/" + id;
        ResponseEntity<String> response = restTemplate.getForEntity(orderServiceUrl, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}
