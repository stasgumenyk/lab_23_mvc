package com.example.demo.Service;


import com.example.demo.Repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


}
