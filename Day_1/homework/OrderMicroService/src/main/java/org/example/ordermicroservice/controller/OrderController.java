package org.example.ordermicroservice.controller;

import org.example.ordermicroservice.model.Order;
import org.example.ordermicroservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }


    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        if(order.getCustomerId() == null) {
            return new ResponseEntity<>("Customer ID can't be null", HttpStatus.BAD_REQUEST); //403 error
        }

        Order orderMade = orderService.addOrder(order);

        if (orderMade == null) {
            return new ResponseEntity<>("Customer not found and order not made", HttpStatus.NOT_FOUND); //404 error if you command click
        }
        return new ResponseEntity<>(orderMade, HttpStatus.CREATED);//201 in console
    }
}

