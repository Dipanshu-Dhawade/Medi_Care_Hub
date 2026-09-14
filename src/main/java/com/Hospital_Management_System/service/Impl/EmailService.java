package com.Hospital_Management_System.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private  final JavaMailSender javaMailSender;

     public void   sendEmail(
             String To,
             String subject,
             String text
     ){
         SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
         simpleMailMessage.setTo(To);
         simpleMailMessage.setSubject(subject);
         simpleMailMessage.setText(text);
       javaMailSender.send(simpleMailMessage);
     }
}

