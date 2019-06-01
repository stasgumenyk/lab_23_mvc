package com.example.demo.Repository;


import com.example.demo.Model.Email;
import com.example.demo.DTO.EmailDto;
import com.example.demo.Repository.Template.RepositoryTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmailRepository extends RepositoryTemplate<Email> {


    @PostConstruct
    private void loadEmails(){
        ObjectMapper mapper = new ObjectMapper();
        List<Email> result = new ArrayList<>();
        String json = null;

        try {
            json = new String(Files.readAllBytes(Paths.get("emails.json")), StandardCharsets.UTF_8);
            Email[] myObjects = mapper.readValue(json, Email[].class);
            result.addAll(Arrays.asList(myObjects));
            //result = Arrays.asList(myObjects);
        } catch (IOException e) {
            //file is empty or not present
        }
        data = result;
    }

    @PreDestroy
    private void saveEmails() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("emails.json"), data );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Email> getUnsentEmails(){
        return getAll().stream()
                .filter(el -> !el.getSend())
                .collect(Collectors.toList());
    }

    public List<Email> getSentEmails(){
        return getAll().stream()
                .filter(Email::getSend)
                .collect(Collectors.toList());
    }

    public List<Email> getEmailsToSend(){
        Date currentDate = new Date();
        return getUnsentEmails().stream()
                .filter(el -> el.getDate().before(currentDate) )
                .collect(Collectors.toList());
    }


    public Email save(EmailDto dto){
        Email saved = new Email(dto);
        return save(saved);
    }


}
