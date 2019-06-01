package com.example.demo.Model;

import com.example.demo.DTO.EmailDto;
import com.example.demo.Model.Interface.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class Email implements Entity {

    private String id;

    private String recipient;

    private String subject;

    private String body;


    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm")
    private Date date;

    private Boolean send;

    public Email(String recipient, String subject, String body, Date date, Boolean send) {
        this.id = "email" + RandomStringUtils.randomAlphanumeric(10);
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.date = date;
        this.send = send;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
