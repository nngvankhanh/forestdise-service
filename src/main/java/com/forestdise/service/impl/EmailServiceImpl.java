package com.forestdise.service.impl;

import com.forestdise.entity.User;
import com.forestdise.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendConfirmEmail(User user, String token) {
        SimpleMailMessage email = new SimpleMailMessage();
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String message = "Thank you for registering to ForestDise." +
                " Please click on this link to verify your account and start shopping:";
        String verifyLink = "https://forestdise.up.railway.app/api" + "/register/confirmation?token=" + token;
        String sentMessage = message + "\n" + verifyLink;
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(sentMessage);
        emailSender.send(email);
    }

    @Override
    public void sendPaymentEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatCurrentDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatCurrentDate);
        String to  = user.getEmail();
        String subject = "Payment has been successful";
        String text = "Hello " + user.getClientName() + "\n" + "\n" +
                "Your order has been placed successfully at " + formattedDate + "\n" +
                "Wishing you always have great experiences when shopping at Forestdise" + "\n" + "\n" +
                "Home page: https://forestdise.vercel.app";
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
