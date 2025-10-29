package com.example.order_service.controller;

import com.example.order_service.entity.Order;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order orderRequest) {
        log.info("Received request to create order for customerId: {}", orderRequest.getCustomerId());

        Order savedOrder = orderService.saveOrder(orderRequest);

        log.info("Order created with Order Number: {}", savedOrder.getOrderNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        log.info("Received request to fetch all orders");
        return orderService.getAllOrders();
    }








}
