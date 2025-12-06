package com.myfinbank.customer.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSend {

    private final JavaMailSender mailSender;

    public MailSend(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendMail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            // "from" will be spring.mail.username by default

            mailSender.send(message);
            return "Sent message successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email: " + e.getMessage();
        }
    }
}
