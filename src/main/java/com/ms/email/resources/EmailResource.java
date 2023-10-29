package com.ms.email.resources;

import com.ms.email.domain.Email;
import com.ms.email.dtos.EmailDto;
import com.ms.email.services.EmailService;
import com.ms.email.services.impl.EmailServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/email")
public class EmailResource {

    private final EmailService emailService;

    public EmailResource(final EmailServiceImpl emailServiceImpl){
        this.emailService = emailServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Email>sendingMail(@RequestBody @Valid EmailDto emailDto){
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        emailService.sendingEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }
}
