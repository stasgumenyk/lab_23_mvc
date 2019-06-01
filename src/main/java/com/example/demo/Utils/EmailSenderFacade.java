package com.example.demo.Utils;


import com.example.demo.Model.Email;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderFacade {

    private JavaMailSender mailSender;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(EmailSenderFacade.class);

    public EmailSenderFacade(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public Boolean sendEmail(Email email){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email.getRecipient());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getBody());
        Boolean result;

        try{
            mailSender.send(mailMessage);
            result = true;
            logger.info("email sent!" );
        }
        catch (MailException ex){
            result = false;
            logger.info("failed to send email");
        }

        return result;
    }

}
