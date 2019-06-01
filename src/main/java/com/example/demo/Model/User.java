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
public class User implements Entity {

    private String id;

    private String username;

    private String emailAddress;

    public User(String username, String emailAddress) {
        this.id = RandomStringUtils.randomAlphanumeric(10);
        this.username = username;
        this.emailAddress = emailAddress;
    }
}
