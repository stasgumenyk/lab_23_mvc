package com.example.demo;

import com.example.demo.Model.Email;
import com.example.demo.Model.Order;
import com.example.demo.Service.DishService;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.UserService;
import com.example.demo.Utils.EmailSenderFacade;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class ScheduledMediator {

    private EmailService emailService;
    private OrderService orderService;
    private UserService userService;
    private DishService dishService;
    private EmailSenderFacade emailSenderFacade;

    public ScheduledMediator(EmailService emailService, OrderService orderService, UserService userService, DishService dishService, EmailSenderFacade emailSenderFacade) {
        this.emailService = emailService;
        this.orderService = orderService;
        this.userService = userService;
        this.dishService = dishService;
        this.emailSenderFacade = emailSenderFacade;
    }

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {

        List<Order> orders = orderService.getAll();
        for (Order order : orders) {
            Email email = new Email();
            email.setSubject(" 'Stasian's Diner' delivery department");
            email.setBody(" Your order has been processed. Be ready for the delivery");
            email.setRecipient(order.getUser().getEmailAddress());
            emailSenderFacade.sendEmail(email);
        }
        orders.clear();
    }
}
