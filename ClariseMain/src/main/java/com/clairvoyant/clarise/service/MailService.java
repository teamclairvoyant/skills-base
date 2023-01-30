package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.User;

import javax.mail.MessagingException;

public interface MailService {

    /**
     *This functionality is not in use
     */
    public void sendEmail(User user) throws MessagingException;
}
