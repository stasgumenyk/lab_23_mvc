package com.example.demo.Service;


import com.example.demo.Model.Email;
import com.example.demo.DTO.EmailDto;
import com.example.demo.Repository.EmailRepository;
import com.example.demo.Service.Template.ServiceTemplate;
import com.example.demo.Utils.EmailAdapter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService extends ServiceTemplate<Email> {


    public EmailService(EmailRepository emailRepository) {
        super(emailRepository);
    }

    public void markAsSent(Email email){
        List<Email> emails = getAll();
        emails.get(emails.indexOf(email)).setSend(email.getSend());
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
        Email saved = EmailAdapter.EmailDtoToEmail(dto);
        return save(saved);
    }

}
