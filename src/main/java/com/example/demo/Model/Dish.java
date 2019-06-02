package com.example.demo.Model;

import com.example.demo.Model.Interface.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
public class Dish implements Entity {

    private String id;

    private String name;

    private String estimatedCookTime;

    private Double price;

    public Dish(String name, String estimatedCookTime, Double price) {
        this.id = "dish" + RandomStringUtils.randomAlphanumeric(10);
        this.name = name;
        this.estimatedCookTime = estimatedCookTime;
        this.price = price;
    }

    public String standartToString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", estimatedCookTime='" + estimatedCookTime + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public String toString() {
        return '\n' + "--------------------------------------------" + '\n' +
                " ID: " + id + '\n' +
                " Назва : " + name + '\n' +
                " Час приготування: " + estimatedCookTime + '\n' +
                " Ціна: " + price + '\n' +
                "--------------------------------------------\n";
    }

}
