package com.example.demo.Model;

import com.example.demo.Model.Interface.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order implements Entity {

    private String id;

    private Dish dish;

    private User user;

    public Order(Dish dish, User user) {
        this.id = "dish" + RandomStringUtils.randomAlphanumeric(10);
        this.dish = dish;
        this.user = user;
    }
}
