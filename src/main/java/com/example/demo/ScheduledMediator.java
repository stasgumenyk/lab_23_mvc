package com.example.demo;

import com.example.demo.Model.Email;
import com.example.demo.Service.EmailService;
import com.example.demo.Utils.EmailSenderFacade;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledMediator {

    private EmailService emailService;
    private EmailSenderFacade emailSenderFacade;

    public ScheduledMediator(EmailService emailService, EmailSenderFacade emailSenderFacade) {
        this.emailService = emailService;
        this.emailSenderFacade = emailSenderFacade;
    }

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        List<Email> emails = emailService.getEmailsToSend();
        for (Email e:emails){
            Boolean isSend = emailSenderFacade.sendEmail(e);
            e.setSend(isSend);
            emailService.markAsSent(e);
        }
    }
}
