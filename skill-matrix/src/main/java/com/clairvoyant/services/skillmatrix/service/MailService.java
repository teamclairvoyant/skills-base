package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.model.User;

import javax.mail.MessagingException;

public interface MailService {

    /**
     *This functionality is not in use
     */
    public void sendEmail(User user) throws MessagingException;
}
