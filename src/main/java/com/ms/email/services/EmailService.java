package com.ms.email.services;

import com.ms.email.domain.Email;

public interface EmailService {

    /**
     * Sending email email.
     *
     * @param email the email
     * @return the email
     */
    Email sendingEmail(Email email);
}
