package com.myfinbank.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSend {

    public static String sendMail(String toEmail, String subject, String body) {

        // TODO: replace with your sender email
        String from = "prasanna.vsp80@gmail.com";

        // Gmail SMTP
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // TODO: replace with your Gmail + app password (not trainer's)
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("prasanna.vsp80@gmail.com", "soqdhechjkcchkgl");
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(body);

            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
            return "Sent message successfully....";

        } catch (MessagingException mex) {
            mex.printStackTrace();
            return mex.getMessage();
        }
    }
}
