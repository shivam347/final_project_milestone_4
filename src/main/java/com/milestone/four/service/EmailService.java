package com.milestone.four.service;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.to}")
    private String receivermail;

    public void sendReport(File file)
            throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper =
            new MimeMessageHelper(message, true);

        helper.setTo(receivermail);
        helper.setSubject("Automation Test Report");
        helper.setText("Please find attached test report.");

        helper.addAttachment(file.getName(), file);

        mailSender.send(message);
    }
    
}
