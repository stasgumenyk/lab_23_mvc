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
@ToString
public class Dish implements Entity {

    private String id;

    private String name;

    private Date estimatedCookTime;

    public Dish(String name, Date estimatedCookTime) {
        this.id = "dish" + RandomStringUtils.randomAlphanumeric(10);
        this.name = name;
        this.estimatedCookTime = estimatedCookTime;
    }

}
