package com.example.api.controller;

import com.example.api.entity.EmailRequest;
import com.example.api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
        return "Email sent successfully to " + request.getTo();
    }
}