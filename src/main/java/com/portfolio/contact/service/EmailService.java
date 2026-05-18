package com.portfolio.contact.service;

// import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.portfolio.contact.model.ContactRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(ContactRequest request){
        try{
             logger.info("=== STARTING EMAIL SEND PROCESS ===");
            logger.info("To: fasilathulfazila@gmail.com");
            logger.info("Subject: Portfolio Contact: " + request.getSubject());
            logger.info("From: " + request.getEmail());
        SimpleMailMessage message = new SimpleMailMessage();
        
         // To your email
        message.setTo("fasilathulfazila@gmail.com");

         
        // Subject
        message.setSubject("Portfolio Contact: "+request.getSubject());

        // Email body
        String emailBody = "Name: "+request.getName() + "\n"+
                           "Email: "+request.getEmail() + "\n"+
                           "Subject: "+request.getSubject()+"\n"+
                           "Message: "+request.getMessage();

        message.setText(emailBody);
        
          // ONLY set reply-to if email is valid
        String replyEmail = request.getEmail();
        if (replyEmail != null && !replyEmail.trim().isEmpty() && replyEmail.contains("@")) {
            message.setReplyTo(replyEmail);
            logger.info("Reply-to set to: " + replyEmail);
        } else {
            logger.warn("No valid reply-to email provided, skipping");
        }
         
         logger.info("Attempting to send email to: fasilathulfazila@gmail.com");
            mailSender.send(message);
            logger.info("Email sent successfully!");
        }
        catch(Exception e){
            logger.error("Failed to send email: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Email sending failed", e);
        }

    }

    
}
