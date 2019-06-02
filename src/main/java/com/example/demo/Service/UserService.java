package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.Template.ServiceTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends ServiceTemplate<User> {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public User findByName(String name){
        return getAll().stream()
                .filter(key-> key.getUsername().equals(name))
                .findFirst()
                .orElse(null);
    }
}
