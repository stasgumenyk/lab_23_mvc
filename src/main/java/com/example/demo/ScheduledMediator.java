package com.example.demo;

import com.example.demo.Model.Email;
import com.example.demo.Model.Order;
import com.example.demo.Service.DishService;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.UserService;
import com.example.demo.Utils.ChainOfResponsibility.UserAuthHandler;
import com.example.demo.Utils.ChainOfResponsibility.UserEmailHandler;
import com.example.demo.Utils.ChainOfResponsibility.UserRoleHandler;
import com.example.demo.Utils.EmailSenderFacade;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@AllArgsConstructor
public class ScheduledMediator {

    private EmailService emailService;
    private OrderService orderService;
    private UserService userService;
    private DishService dishService;
    private EmailSenderFacade emailSenderFacade;
    private UserAuthHandler authHandler;
    private UserEmailHandler emailHandler;
    private UserRoleHandler roleHandler;


    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {

        authHandler.setNext(roleHandler);
        roleHandler.setNext(emailHandler);

        List<Order> orders = orderService.getAll();
        for (Order order : orders) {
            if (authHandler.handle(order)){
                Email email = new Email();
                email.setSubject(" 'Stasian's Diner' delivery department");
                email.setBody(" Your order has been processed. Be ready for the delivery");
                email.setRecipient(order.getUser().getEmailAddress());
                emailSenderFacade.sendEmail(email);
            }
        }
        orders.clear();
    }
}
