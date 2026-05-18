package com.portfolio.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.contact.model.ContactRequest;
import com.portfolio.contact.service.EmailService;


@RestController
@RequestMapping("api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody ContactRequest request){
        try{
            emailService.sendEmail(request);
            return "Message sent successfully! ✅";
        }
        catch(Exception e){
            return "Failed to send message: "+e.getMessage();
        }

    }
}