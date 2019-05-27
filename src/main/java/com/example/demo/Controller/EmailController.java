package com.example.demo.Controller;


import com.example.demo.Model.Email;
import com.example.demo.DTO.EmailDto;
import com.example.demo.Service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/emails")
public class EmailController {

    private EmailService emailService;

    @GetMapping(value = "/pending")
    public ResponseEntity<List<Email>> getPendingEmails() {
        return ResponseEntity.ok(emailService.getUnsentEmails());
    }

    @DeleteMapping("/{emailId}")
    public ResponseEntity removePendingEmail(@PathVariable("emailId") String emailId) {
        return emailService.remove(emailId)
                ? ResponseEntity.status(HttpStatus.OK).body("Deleted email successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email with id " + emailId + " not found" );
        }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePendingEmail(@RequestBody Email updates) {
        return emailService.update(updates)
                ? ResponseEntity.status(HttpStatus.OK).body("Updated email successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email with id " + updates.getId() + " not found" );
        }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Email> createEmailFromJson(@RequestBody EmailDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(emailService.save(dto));
    }

}
