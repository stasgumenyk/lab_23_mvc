package com.example.demo.Utils.ChainOfResponsibility;

import com.example.demo.Model.Enums.Role;
import com.example.demo.Model.Order;
import org.springframework.stereotype.Component;

@Component
public class UserRoleHandler implements OrderHandler {

    private OrderHandler next;

    @Override
    public void setNext(OrderHandler next) {
        this.next = next;
    }

    @Override
    public Boolean handle(Order order) {
        Boolean result = order.getUser().getRole() == Role.USER;
        if (next != null){
            result = result && next.handle(order);
        }
        return result;
    }
}
