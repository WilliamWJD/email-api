package com.ms.email.services.impl;

import com.ms.email.domain.Email;
import com.ms.email.enums.StatusEmail;
import com.ms.email.repositories.EmailRepository;
import com.ms.email.services.EmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender emailSender;

    public EmailServiceImpl(final EmailRepository repository, final JavaMailSenderImpl javaMailSender){
        this.emailRepository = repository;
        this.emailSender = javaMailSender;
    }

    @Override
    public Email sendingEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubjetc());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        }finally {
            return emailRepository.save(email);
        }
    }
}
