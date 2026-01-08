package com.shidori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordEmail(String toEmail, String name, String password) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your Account Password");
        message.setText(
                "Hello " + name + ",\n\n" +
                "Your account has been created successfully.\n\n" +
                "Your login password is: " + password + "\n\n" +
                "Please change your password after first login.\n\n" +
                "Regards,\nSupport Team"
        );

        mailSender.send(message);
    }
}
