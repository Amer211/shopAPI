package com.example.order_service.service;

import com.example.order_service.entity.Order;
import com.example.order_service.entity.Status;
import com.example.order_service.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(Order order){

        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PENDING);
        order.setOrderNumber(UUID.randomUUID().toString());

        log.info("Saving order: {}", order.getOrderNumber());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        log.info("Fetching all orders");
        return  orderRepository.findAll();
    }
    public Order findOrderById(Long id){
        log.info("fetching order by id: {}",id);
        return orderRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order not found with id: "+id));
    }



}//service class
