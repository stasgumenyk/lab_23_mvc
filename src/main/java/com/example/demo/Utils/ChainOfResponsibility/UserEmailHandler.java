package com.example.demo.Utils.ChainOfResponsibility;

import com.example.demo.Model.Order;
import com.example.demo.Utils.Validator;
import org.springframework.stereotype.Component;

@Component
public class UserEmailHandler implements OrderHandler {

    private OrderHandler next;
    private Validator validator;

    public UserEmailHandler(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void setNext(OrderHandler next) {
        this.next = next;
    }

    @Override
    public Boolean handle(Order order) {
        Boolean result = validator.isEmailValid(order.getUser().getEmailAddress());
        if (next != null){
            result = result && next.handle(order);
        }
        return result;
    }
}
