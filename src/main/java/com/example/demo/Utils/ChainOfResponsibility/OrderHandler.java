package com.example.demo.Utils.ChainOfResponsibility;

import com.example.demo.Model.Order;

public interface OrderHandler {

        void setNext(OrderHandler next);

        Boolean handle(Order order);
}
