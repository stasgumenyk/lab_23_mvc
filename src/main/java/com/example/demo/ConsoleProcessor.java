package com.example.demo;

import com.example.demo.Model.Dish;
import com.example.demo.Model.Email;
import com.example.demo.Model.Order;
import com.example.demo.Model.User;
import com.example.demo.Service.DishService;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.UserService;
import com.example.demo.Utils.Validator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Scanner;


@Component
public class ConsoleProcessor implements CommandLineRunner {

    private EmailService emailService;
    private DishService dishService;
    private OrderService orderService;
    private UserService userService;
    private Validator validator;



    public ConsoleProcessor(EmailService emailService, DishService dishService, OrderService orderService, UserService userService, Validator validator) {
        this.emailService = emailService;
        this.dishService = dishService;
        this.orderService = orderService;
        this.userService = userService;
        this.validator = validator;
    }

    private User loginedUser;

    private void initStuff(){
        loginedUser = userService.findByName("stasgumenyk");
        if (loginedUser == null){
            loginedUser = new User.UserBuilder("stasgumenyk")
                    .setActivated(true)
                    .setEmailAddress("stasgumenyk@gmail.com")
                    .build();
            userService.save(loginedUser);
        }


        if (dishService.findByName("Burger") == null){
            Dish burger = new Dish("Burger", "15 minutes", 7.99);
            dishService.add(burger);
        }

        if (dishService.findByName("Cola") == null){
            Dish cola = new Dish("Cola", "1 minute", 2.99);
            dishService.add(cola);
        }

        if (dishService.findByName("Fries") == null){
            Dish fries = new Dish("Fries", "3 minutes", 5.75);
            dishService.add(fries);
        }

        if (dishService.findByName("Test") == null){
            Dish test = new Dish("Test", "0 minutes", 0.99);
            dishService.add(test);
        }


    }

    @Override
    public void run(String... args) throws Exception {

        initStuff();

        Boolean alive = true;
        while (alive){
            System.out.println("You are logined as " + loginedUser.getUsername());
            System.out.println("What would you like to do? \nType in console to:");
            System.out.println("1 - print menu");
            System.out.println("2 - print all orders");
            System.out.println("3 - order a dish");
            Scanner consoleScanner = new Scanner(System.in);
            String input = consoleScanner.nextLine();
            switch (input){
                case "1":
                    System.out.println(dishService.getAll());
                    break;
                case "2":
                    System.out.println(orderService.getAll());
                    break;
                case "3":
                    System.out.println("Enter id of a dish:");
                    String id = consoleScanner.nextLine();

                    Dish dish = dishService.get(id);
                    Order order = new Order(dish, loginedUser);
                    orderService.add(order);

                    break;
                default:
                    System.out.println("Farewell, stranger");
                    alive = false;
                    break;
            }
            System.out.println("Press anything to continue...");
            consoleScanner.nextLine();
        }

    }
}
