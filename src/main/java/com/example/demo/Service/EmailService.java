package com.example.demo.Service;


import com.example.demo.Model.Email;
import com.example.demo.DTO.EmailDto;
import com.example.demo.Repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public void markAsSent(Email email){
        List<Email> emails = emailRepository.getAllEmails();
        emails.get(emails.indexOf(email)).setSend(email.getSend());
    }

    public Boolean remove(String id){
        return emailRepository.remove(id);
    }

    public Boolean update(Email email){
        return emailRepository.update(email);
    }

    public Email save(EmailDto dto){
        return emailRepository.save(dto);
    }

    public void add(Email email){
        emailRepository.add(email);
    }

    public List<Email> getAllEmails(){
        return emailRepository.getAllEmails();
    }

    public List<Email> getSentEmails(){
        return emailRepository.getSentEmails();
    }

    public List<Email> getUnsentEmails(){
        return emailRepository.getUnsentEmails();
    }

    public List<Email> getEmailsToSend(){
        return emailRepository.getEmailsToSend();
    }

}
