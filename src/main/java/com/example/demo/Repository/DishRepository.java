package com.example.demo.Repository;


import com.example.demo.Model.Dish;
import com.example.demo.Model.User;
import com.example.demo.Repository.Template.RepositoryTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DishRepository extends RepositoryTemplate<Dish> {

    @PostConstruct
    private void loadDishes(){
        ObjectMapper mapper = new ObjectMapper();
        List<Dish> result = new ArrayList<>();
        String json = null;

        try {
            json = new String(Files.readAllBytes(Paths.get("pseudoDB/dishes.json")), StandardCharsets.UTF_8);
            Dish[] myObjects = mapper.readValue(json, Dish[].class);
            result.addAll(Arrays.asList(myObjects));
            //result = Arrays.asList(myObjects);
        } catch (IOException e) {
            //file is empty or not present
        }
        data = result;
    }

    @PreDestroy
    private void saveUsers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("pseudoDB/dishes.json"), data );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
