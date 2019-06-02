package com.example.demo.Model;


import com.example.demo.Model.Enums.Role;
import com.example.demo.Model.Interface.Entity;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User implements Entity {

    private String id;

    private String username;

    private String emailAddress;

    private Boolean activated;

    private Role role;

    private List<String> privileges;

    public User(String username) {
        this.id = "user" + RandomStringUtils.randomAlphanumeric(10);
        this.role = Role.USER;
        this.username = username;
    }

    public User(UserBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.emailAddress = builder.emailAddress;
        this.activated = builder.activated;
        this.role = builder.role;
        this.privileges = builder.privileges;
    }

    public static class UserBuilder{

        private String id;

        private String username;

        private String emailAddress;

        private Boolean activated;

        private Role role;

        private List<String> privileges;

        public UserBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public UserBuilder setActivated(Boolean activated) {
            this.activated = activated;
            return this;
        }

        public UserBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder setPrivileges(List<String> privileges) {
            this.privileges = privileges;
            return this;
        }

        public UserBuilder(String username) {
            this.id = "user" + RandomStringUtils.randomAlphanumeric(10);
            this.role = Role.USER;
            this.username = username;
        }

        public User build(){
            return new User(this);
        }
    }


}
