package com.ms.email.consumers;

import com.ms.email.domain.Email;
import com.ms.email.dtos.EmailDto;
import com.ms.email.services.EmailService;
import com.ms.email.services.impl.EmailServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(final EmailServiceImpl emailServiceImpl){
        this.emailService = emailServiceImpl;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto emailDto){
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        emailService.sendingEmail(email);
        System.out.println("Email status: "+email.getStatusEmail().toString());
    }
}
