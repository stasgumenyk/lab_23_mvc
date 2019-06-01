package com.example.demo.Utils;

import com.example.demo.DTO.EmailDto;
import com.example.demo.Model.Email;
import org.apache.commons.lang3.RandomStringUtils;

public class EmailAdapter {

    public static Email EmailDtoToEmail(EmailDto dto){
        Email email = new Email();
        email.setId(RandomStringUtils.randomAlphanumeric(10));
        email.setRecipient(dto.getRecipient());
        email.setSubject(dto.getSubject());
        email.setBody(dto.getBody());
        email.setDate(dto.getDate());
        email.setSend(false);

        return email;
    }
}
