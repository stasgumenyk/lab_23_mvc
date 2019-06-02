package com.example.demo.Service;

import com.example.demo.Model.Dish;
import com.example.demo.Model.User;
import com.example.demo.Repository.DishRepository;
import com.example.demo.Repository.Template.RepositoryTemplate;
import com.example.demo.Service.Template.ServiceTemplate;
import org.springframework.stereotype.Service;

@Service
public class DishService extends ServiceTemplate<Dish> {

    public DishService(DishRepository repository) {
        super(repository);
    }

    public Dish findByName(String name){
        return getAll().stream()
                .filter(key-> key.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
