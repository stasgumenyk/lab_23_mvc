package com.example.demo.Repository;

import com.example.demo.Model.Order;
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
public class OrderRepository extends RepositoryTemplate<Order> {

    @PostConstruct
    private void loadOrders(){
        ObjectMapper mapper = new ObjectMapper();
        List<Order> result = new ArrayList<>();
        String json = null;

        try {
            json = new String(Files.readAllBytes(Paths.get("pseudoDB/orders.json")), StandardCharsets.UTF_8);
            Order[] myObjects = mapper.readValue(json, Order[].class);
            result.addAll(Arrays.asList(myObjects));
            //result = Arrays.asList(myObjects);
        } catch (IOException e) {
            //file is empty or not present
        }
        data = result;
    }

    @PreDestroy
    private void saveOrders() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("pseudoDB/orders.json"), data );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
