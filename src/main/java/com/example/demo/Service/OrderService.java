package com.example.demo.Service;


import com.example.demo.Model.Order;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.Template.ServiceTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends ServiceTemplate<Order> {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        super(orderRepository);
    }

}
