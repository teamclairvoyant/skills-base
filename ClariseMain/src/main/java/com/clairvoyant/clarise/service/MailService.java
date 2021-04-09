package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.User;

import javax.mail.MessagingException;

public interface MailService {

    public void sendEmail(User user) throws MessagingException;
}
